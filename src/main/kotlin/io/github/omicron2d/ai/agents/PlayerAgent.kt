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
 * @param isGoalie if the agent joined as a goalie
 */
class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT,
        private val isGoalie: Boolean = false) : AbstractSoccerAgent(host, port), PlayerMessageHandler {
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
            // after this: all handlers have been called, low level world model is setup, high level needs processing

            // check to make sure we have enough flags to perform a decent localisation with, about three should
            // be fine - I can't imagine it would work with any less
            if (lowModel.goodFlags.size >= 3) {
                // first we convert our messy flag data into polar flag observations that the localiser requires
                // which is 0-360 instead of -180-180
                val observations = mutableMapOf<String, ObjectObservationPolar>()
                // NOTE that we can be assured that distance and direction are NOT null, because flags missing those
                // are filtered out for goodFlags in handleSeeMessage below
                for (flag in lowModel.goodFlags) {
                    val direction = (flag.direction!!.toDouble() + 360.0) % 360.0
                    observations[flag.name] = ObjectObservationPolar(flag.distance!!, direction)
                }
                val agentTransform = ICPLocalisation.performLocalisation(observations)

                // reset previously recorded information about unknown players since we have data
                highModel.unknownTeamPlayers.clear()
                highModel.unknownPlayers.clear()

                // big world model update section
                // calculate absolute positions for other players
                for (player in lowModel.players){
                    val info = player.playerInfo
                    val dist = player.distance
                    val dir = player.direction
                    // in future we could perhaps do something if dist or direction were null, currently we ignore
                    if (dist == null || dir == null){
                        Logger.trace("Player $player has no useful information, skipping")
                        continue
                    }

                    if (info?.unum != null && info.teamName != null){
                        // we have lots of information: we can find out this player's id, and which team they're on
                        val id = info.unum!! - 1
                        if (info.teamName == CURRENT_CONFIG.get().teamName){
                            // it's our team
                            // note that we use ID here instead of unum since teamPlayers is zero indexed
                            highModel.teamPlayers[id].isKnown = true
                            highModel.teamPlayers[id].lastSeen = lowModel.time
                            highModel.teamPlayers[id].isGoalie = info.goalie
                            val absolute = calculateAbsolutePosition(dir, dist, agentTransform)
                            highModel.teamPlayers[id].transform = Transform2D(absolute, 0.0)
                            // TODO calculate their orientation if available
                        } else {
                            // it's the opposition team
                            highModel.opponentPlayers[id].isKnown = true
                            highModel.opponentPlayers[id].lastSeen = lowModel.time
                            highModel.opponentPlayers[id].isGoalie = info.goalie
                            val absolute = calculateAbsolutePosition(dir, dist, agentTransform)
                            highModel.teamPlayers[id].transform = Transform2D(absolute, 0.0)
                            // TODO calculate their orientation if available
                        }
                    } else if (info?.unum != null){
                        // we know this player's id, we might be able to infer which team they're on
                        // TODO work out a way to infer team for certain players
                        // we could even calculate chances of which player is which (e.g. 50-50 chance it's id 2 or 3)
                        val absolute = calculateAbsolutePosition(dir, dist, agentTransform)
                        val transform = Transform2D(absolute, 0.0)
                        // TODO calculate their orientation if available

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
                        val absolute = calculateAbsolutePosition(dir, dist, agentTransform)
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
                    if (ball.direction == null || ball.distance == null){
                        Logger.warn("Cannot localise ball, distance or direction is null: $ball")
                        // here we may want to ask our friends for help
                    } else {
                        val absolute = calculateAbsolutePosition(ball.direction!!, ball.distance!!, agentTransform)
                        highModel.ball.pos = absolute
                        highModel.ball.isKnown = true
                        highModel.ball.lastSeen = lowModel.time
                        // TODO calculate velocity vector?
                    }
                } else {
                    highModel.ball.isKnown = false
                }

                // finally, we update the high level world model for ourselves
                val self = highModel.selfId
                highModel.teamPlayers[self].isKnown = true
                highModel.teamPlayers[self].transform = agentTransform
                highModel.teamPlayers[self].lastSeen = lowModel.time
                AGENT_STATS.get().successfulLocalisations++
            } else {
                // only log if game has started to reduce spam
                Logger.trace("Cannot perform localisation, no good flags! all flags = ${lowModel.allFlags}")

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

                AGENT_STATS.get().failedLocalisations++
            }

            // TODO temporary just for fun
            val mode = arrayOf(ViewMode.NARROW, ViewMode.NORMAL, ViewMode.WIDE).random()
            val quality = arrayOf(ViewQuality.HIGH, ViewQuality.LOW).random()
            transmit(arrayOf(TurnMessage(30.0), ChangeViewMessage(mode, ViewQuality.HIGH)))
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
        highModel.teamPlayers[highModel.selfId].isGoalie = isGoalie
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
        //pushBatch(SynchSeeMessage())
        // TODO decide if we actually want to send synch see message? what does it do?
        flushBatch()
    }

    override fun handleSeeMessage(see: SeeMessage){
        val flags = see.objects.filter { it.type == ObjectType.FLAG }
        // good flags are the ones we use to localise
        lowModel.goodFlags = flags.filter {
            it.name.isNotEmpty() && !it.isBehind && it.direction != null && it.distance != null
        }
        lowModel.allFlags = flags
        lowModel.players = see.objects.filter { it.type == ObjectType.PLAYER }
        lowModel.ball = see.objects.firstOrNull { it.type == ObjectType.BALL }
        lowModel.updateTime(see.time)
        Logger.trace("Received see message: $see")

        AGENT_STATS.get().goodFlagsRate.addValue(lowModel.goodFlags.size.toDouble() / lowModel.allFlags.size.toDouble())
    }

    override fun handleHearMessage(hear: HearMessage){
        Logger.debug("Received hear message: $hear")
        lowModel.updateTime(hear.time)
        val ourCoach = if (highModel.selfSide == Side.LEFT) MessageSender.ONLINE_COACH_LEFT else MessageSender.ONLINE_COACH_RIGHT

        when {
            hear.sender == MessageSender.REFEREE -> {
                // it's from the ref, either playmode change or we have fouled or something
            }
            hear.sender == MessageSender.COACH || hear.sender == ourCoach -> {
                // it's a coach, listen to what they've got to say
            }
            hear.direction != null -> {
                // it's sent by another player
            }
        }
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
//        if (errorCount > 0){
//            errorCount--
//        }
    }

    override fun handleWarningMessage(warning: WarningMessage) {
        Logger.warn("Received server warning: ${warning.message}")
    }
}