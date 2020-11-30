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
import io.github.omicron2d.ai.world.HighLevelWorldModel
import io.github.omicron2d.ai.world.ICPLocalisation
import io.github.omicron2d.ai.world.LowLevelWorldModel
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
    private val startingFormation = Formation(CURRENT_CONFIG.get().initialFormation)

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
                val observations = mutableMapOf<String, ObjectObservationPolar>()
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
                        val id = info.unum!! - 1
                        if (info.teamName == CURRENT_CONFIG.get().teamName){
                            // it's our team
                            // note that we use ID here instead of unum since teamPlayers is zero indexed
                            highModel.teamPlayers[id].isKnown = true
                            highModel.teamPlayers[id].lastSeen = lowModel.time
                            highModel.teamPlayers[id].isGoalie = info.goalie
                            val absolute = calculateAbsolutePosition(player.direction, player.distance, agentTransform)
                            highModel.teamPlayers[id].transform = Transform2D(absolute, 0.0)
                            // TODO can we calculate the orientation of them as well?
                        } else {
                            // must be the opposition team
                            highModel.opponentPlayers[id].isKnown = true
                            highModel.opponentPlayers[id].lastSeen = lowModel.time
                            highModel.opponentPlayers[id].isGoalie = info.goalie
                            val absolute = calculateAbsolutePosition(player.direction, player.distance, agentTransform)
                            highModel.teamPlayers[id].transform = Transform2D(absolute, 0.0)
                        }
                    } else if (info?.unum != null){
                        // we know this player's id, we might be able to infer which team they're on
                        // TODO work out a way to infer team - in the mean time we're just dumping them in unknowns
                        // we could even calculate percentage chances of which player is which (e.g. 50-50 chance it's id 2 or 3)

                        val absolute = calculateAbsolutePosition(player.direction, player.distance, agentTransform)
                        val transform = Transform2D(absolute, 0.0)
                        // TODO calculate orientation as well

                        val obj = PlayerObject().apply {
                            this.transform = transform
                            isKnown = true
                            unum = info.unum!!
                            id = info.unum!! - 1
                            isGoalie = info.goalie
                        }
                        highModel.unknownTeamPlayers.add(obj)
                    } else {
                        // we don't know anything about this player, but still set them up for obstacle avoidance
                        val absolute = calculateAbsolutePosition(player.direction, player.distance, agentTransform)
                        val transform = Transform2D(absolute, 0.0)
                        // TODO calculate orientation as well

                        val obj = PlayerObject().apply {
                            this.transform = transform
                            isKnown = true
                        }
                        highModel.unknownPlayers.add(obj)
                    }
                }

                // calculate absolute position of the ball
                if (lowModel.ball != null){
                    val ball = lowModel.ball!!
                    val absolute = calculateAbsolutePosition(ball.direction, ball.distance, agentTransform)
                    highModel.ball.pos = absolute
                    highModel.ball.isKnown = true
                    highModel.ball.lastSeen = lowModel.time
                    // TODO calculate velocity vector?
                } else {
                    highModel.ball.isKnown = false
                }

                // finally, we update the high level world model for ourselves
                val self = highModel.selfId
                highModel.teamPlayers[self].isKnown = true
                highModel.teamPlayers[self].transform = agentTransform
                highModel.teamPlayers[self].lastSeen = lowModel.time
                CURRENT_STATS.get().successfulLocalisations++
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
                CURRENT_STATS.get().failedLocalisations++
            }
        }
    }

    override fun handleInitMessage(init: IncomingInitMessage){
        if (init.playMode != PlayMode.BEFORE_KICK_OFF){
            Logger.warn("Unexpected play mode during init: ${init.playMode} (agent joined late?)")
        }
        // first, we update our world models
        lowModel.selfUnum = init.unum
        lowModel.selfSide = init.side

        highModel.selfId = init.unum - 1 // note: id is zero indexed, unum is one indexed
        highModel.selfSide = init.side
        highModel.teamPlayers[highModel.selfId].isSelf = true
        Logger.info("Init message received: $init (self ID: ${highModel.selfId})")

        // now, we send back stuff to the server, setting up our agents settings basically
        // Okay! So, for whatever idiot reason, the server (for the move command) actually considers right-side
        // coordinates as exactly the same as left side coordinates. So, instead of moving to (20, 0) for example,
        // the ACTUAL position... we move to (-20, 0) - the same as the left side. This means that no coordinate
        // adjustment is actually required.
        // once we have our unum, load the specified formation and move to our position
        val pos = startingFormation.getPosition(highModel.selfId)
        Logger.debug("Moving to initial position $pos for formation ${startingFormation.name}")
        pushBatch(MoveMessage(pos))

        // we also disable hearing the opposition since we don't care about that
        // this was found by looking at the logs for FRA-UNited
        pushBatch(EarMessage(status = CURRENT_CONFIG.get().listenToOpposition, us = false))
        pushBatch(SynchSeeMessage())
        // TODO decide if we actually want to send synch see message? what does it do?

        flushBatch()
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

        if (hear.sender == MessageSender.REFEREE){
            // it's a playmode change, we need to update our behaviour
        } else if (hear.direction != null){
            // it's sent by another player
        }
        // currently all other conditions are ignored
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
        // decrease our error count, don't set to zero in case we get spaced out errors
        // (we still need to do the emergency exit then)
        if (errorCount > 0){
            errorCount--
        }
    }

    override fun handleWarningMessage(warning: WarningMessage) {
        Logger.warn("Received server warning: ${warning.message}")
    }
}