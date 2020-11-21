/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.agents

import io.github.omicron2d.ai.Formation
import io.github.omicron2d.ai.world.*
import io.github.omicron2d.communication.AbstractSoccerAgent
import io.github.omicron2d.communication.messages.*
import io.github.omicron2d.utils.*
import org.tinylog.kotlin.Logger
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

/**
 * Class for all standard soccer agents (players) including the goalie.
 * This is the class in which the main information processing pipeline takes place.
 */
class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port), PlayerMessageHandler {
    private val lowModel = LowLevelWorldModel()
    private val highModel = HighLevelWorldModel()
    private var errorCount = 0
    private val startingFormation = Formation(currentConfig.initialFormation)

    override fun run() {
        Logger.debug("PlayerAgent main loop started")

        while (true){
            // Receive message from server and parse
            val msgStr = messages.poll(30, TimeUnit.SECONDS)
            if (msgStr == null){
                Logger.warn("Unexpected null message from message queue, server dead? Terminating!")
                break
            } else if (msgStr == "INTERNAL_TIMED_OUT"){
                Logger.warn("Received server timeout message, terminating PlayerAgent!")
                break
            }

            // Dispatch message to handlers for data processing. Runs in same thread, no need to worry about data races
            dispatchMessage(msgStr)

            // With all of our data now processed in the LowLevelWorldModel, we now perform localisation using the ICP
            // algorithm. In this long block, we also update all our object positions with the localised data.
            // This is complicated by the fact that we may want to use old data so we can't clear it every tick, the
            // currently poor storage (we have to do .indices bc it's stored in an array which we want to update), and
            // finally the fact that rcssserver can send us incomplete data (such as players with no team names) that
            // we have to deal with and process.

            // First we convert our messy flag data into polar flag observations that the localiser requires
            if (lowModel.goodFlags.isNotEmpty()) {
                val observations = hashMapOf<String, ObjectObservationPolar>()
                // convert each flag's angle from -180 to 180 (from server) to 0 to 360 (for localiser)
                for (flag in lowModel.goodFlags) {
                    val direction = (flag.direction.toDouble() + 360.0) % 360.0
                    observations[flag.name] = ObjectObservationPolar(flag.distance, direction)
                }
                val agentTransform = ICPLocalisation.performLocalisation(observations)

                // first, reset previously recorded information about unknown players since we have data
                highModel.unknownTeamPlayers.clear()
                highModel.unknownPlayers.clear()

                // calculate absolute positions for other players
                for (player in lowModel.players){
                    val info = player.playerInfo
                    if (info?.unum != null && info.teamName != null){
                        // we have lots of information: we can find out this player's id, and which team they're on
                        // first figure out which team they're on
                        if (info.teamName == currentConfig.teamName){
                            // it's our team
                            // TODO make this code its own method for DRY
                            highModel.teamPlayers[info.unum!!].isKnown = true
                            highModel.teamPlayers[info.unum!!].lastSeen = lowModel.time
                            highModel.teamPlayers[info.unum!!].isGoalie = info.goalie

                            val direction = (player.direction.toDouble() % 360.0) % 360.0
                            val observation = ObjectObservationPolar(player.distance, direction)
                            val absolute = ICPLocalisation.correctPolarObservation(observation, agentTransform.pos)
                            highModel.teamPlayers[info.unum!!].transform = Transform2D(absolute, 0.0)
                            // TODO can we calculate the orientation of them as well?
                        } else {
                            // must be the opposition
                        }
                    } else if (info?.unum != null){
                        // we know this player's id, we might be able to infer which team they're on (if we observe
                        // all the players on our team for example)
                    } else {
                        // we don't know anything about this player, but still set them up for obstacle avoidance
                    }
                }

                // calculate absolute position of the ball
                if (lowModel.ball != null){
                    // TODO inline this conversion code into one function for DRY
                    val ball = lowModel.ball!!
                    val direction = (ball.direction.toDouble() % 360.0) % 360.0
                    val observation = ObjectObservationPolar(ball.distance, direction)
                    val absolute = ICPLocalisation.correctPolarObservation(observation, agentTransform.pos)

                    highModel.ball.pos = absolute
                    highModel.ball.isKnown = true
                    highModel.ball.lastSeen = lowModel.time
                } else {
                    highModel.ball.isKnown = false
                }

                // finally, we update the high level world model for ourselves
                val self = highModel.selfUnum
                highModel.teamPlayers[self].isKnown = true
                highModel.teamPlayers[self].transform = agentTransform
                highModel.teamPlayers[self].lastSeen = lowModel.time
            } else {
                // TODO figure out a way to log this without spamming on startup
                //Logger.warn("Cannot perform localisation, no good flags!")
                //Logger.warn("All flags: ${lowModel.allFlags}")

                // because we couldn't localise, this means that the positions of all our localised objects are
                // now unknown. so go and update them here
                for (i in highModel.teamPlayers.indices){
                    highModel.teamPlayers[i].isKnown = false
                    highModel.opponentPlayers[i].isKnown = false
                }
                // we don't clear out unknown player data if we have no flags, since we might want to work with it later
                // (just being aware that we will have to extrapolate their positions based on velocity)
                for (i in highModel.unknownTeamPlayers.indices){
                    highModel.unknownTeamPlayers[i].isKnown = false
                }
                for (i in highModel.unknownPlayers.indices){
                    highModel.unknownPlayers[i].isKnown = false
                }
                highModel.ball.isKnown = false
            }
        }
    }

    override fun handleInitMessage(init: IncomingInitMessage){
        if (init.playMode != PlayMode.BEFORE_KICK_OFF){
            Logger.warn("Unexpected play mode during init: ${init.playMode} (agent joined late?)")
        }
        lowModel.selfUnum = init.side
        lowModel.selfSide = init.unum
        highModel.selfUnum = init.unum
        highModel.selfSide = init.side
        highModel.teamPlayers[init.unum].isSelf = true
        // FIXME the bloody agent unums are one indexed, so we will have to convert it
        Logger.info("Init message received: $init")

        // once we have our unum, load the 433 formation and move to our position
        val pos = startingFormation.getPosition(highModel.selfUnum)
        transmit(MoveMessage(pos.x, pos.y))
        Logger.debug("Moved to initial position $pos for unum ${highModel.selfUnum}")
    }

    override fun handleSeeMessage(see: SeeMessage){
        // filter out only flags in the see message
        val flags = see.objects.filter { it.type == ObjectType.FLAG }
        lowModel.goodFlags = flags.filter { it.name.isNotEmpty() && !it.isBehind }
        lowModel.allFlags = flags
        lowModel.players = see.objects.filter { it.type == ObjectType.PLAYER }
        lowModel.ball = see.objects.firstOrNull { it.type == ObjectType.BALL }
        lowModel.time = see.time
        Logger.trace("Received see message: $see")
    }

    override fun handleHearMessage(hear: HearMessage){
        Logger.debug("Received hear message: $hear")
        lowModel.time = hear.time
    }

    override fun handleErrorMessage(error: ErrorMessage){
        Logger.warn("Received server error: ${error.message}")

        // if we don't do this, the client and server will keep spamming each other and blow up
        if (errorCount++ > 8){
            Logger.error("Too many errors, performing emergency exit!")
            exitProcess(1)
        }
    }

    override fun handleAnyMessage() {
        errorCount = 0
    }

    override fun handleWarningMessage(warning: WarningMessage) {
        Logger.warn("Received server warning: ${warning.message}")
    }
}