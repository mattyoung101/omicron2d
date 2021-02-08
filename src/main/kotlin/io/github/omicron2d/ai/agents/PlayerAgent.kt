/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.agents

import io.github.omicron2d.ai.EventStates
import io.github.omicron2d.ai.Formation
import io.github.omicron2d.ai.behaviours.CommsManager
import io.github.omicron2d.ai.behaviours.MovementManager
import io.github.omicron2d.ai.behaviours.lowlevel.TurnBodyTo
import io.github.omicron2d.ai.behaviours.lowlevel.Wait
import io.github.omicron2d.ai.world.HighLevelWorldModel
import io.github.omicron2d.ai.world.ICPLocalisation
import io.github.omicron2d.ai.world.LowLevelWorldModel
import io.github.omicron2d.communication.AbstractSoccerAgent
import io.github.omicron2d.communication.messages.*
import io.github.omicron2d.utils.*
import org.apache.commons.lang3.concurrent.BasicThreadFactory
import org.tinylog.kotlin.Logger
import java.net.InetAddress
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.abs
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
    private val movementManager = MovementManager()
    private val commsManager = CommsManager()
    private var errorCount = 0
    private val startingFormation = Formation(CURRENT_CONFIG.get().initialFormation)
    // we use this instead of timer, see https://stackoverflow.com/a/409993/5007892
    private val thinkTimer = Executors.newSingleThreadScheduledExecutor(namedThreadFactory)
    private val eventState = EventStates()

    /**
     * This function is intermittently called every 99ms, to send data to the server, since our message receive rate
     * does not equal how often we should be sending messages. (We leave 1ms for transport to the server).
     */
    private fun think(){
        val ctx = AgentContext(highModel, lowModel.time)

        val movement = movementManager.tickMovement(ctx)
        val comms = commsManager.tickCommunications(ctx)

        // update agent movement
        // if the dash power here is too small, don't bother sending the message to the server to reduce spam
        if (movement.dash != null && abs(movement.dash.x) >= EPSILON){
            // convert radians to degrees, then 0-360 degrees to -180 to +180 degrees
            val dashDirDegrees = movement.dash.y.toDegrees()
            val dashDir = if (dashDirDegrees > 180.0) dashDirDegrees - 360.0 else dashDirDegrees

            transmit(DashMessage(movement.dash.x, dashDir))
        } else if (movement.turn != null && abs(movement.turn) >= EPSILON){
            // same angle conversion as above, and also don't transmit if turn angle is too small
            val angleDeg = movement.turn.toDegrees()
            val angle = if (angleDeg > 180.0) angleDeg - 360.0 else angleDeg

            transmit(TurnMessage(angle))
        }

        // update agent communications
        if (comms.isNotEmpty()){

        }
    }

    override fun run() {
        Logger.debug("PlayerAgent main loop started")

        while (true) {
            // Receive message from server and parse
            // this timeUnit thing is a hack so that when we're debugging it doesn't keep quitting
            val timeUnit = if (System.getProperty("ignoreNullServerMessages") == null) TimeUnit.SECONDS else TimeUnit.HOURS
            val msgStr = messages.poll(30, timeUnit)
            if (msgStr == null) {
                Logger.warn("Unexpected null message from message queue, server dead? Terminating!")
                return
            } else if (msgStr == "INTERNAL_TIMED_OUT") {
                Logger.warn("Received server timeout message, terminating PlayerAgent!")
                return
            }

            // Dispatch message to handlers for data processing. Runs in same thread, no need to worry about data races
            dispatchMessage(msgStr)

            // If we've received the first packet (and thus have switched ports), start the think timer to send
            // commands back
            if (!eventState.hasReceivedMessage) {
                Logger.debug("First message received, starting think timer")
                thinkTimer.scheduleAtFixedRate({ think() }, 0, 99, TimeUnit.MILLISECONDS)
                eventState.hasReceivedMessage = true
            }
        }
    }

    /** Event listener called when playmode is changed */
    private fun onPlayModeChange(newMode: PlayMode){
        highModel.playMode = newMode

        // check for initial play_on
        // TODO also check for our kickoff (kick_off_l/kick_off_r depending)
        if (highModel.playMode == PlayMode.PLAY_ON && !eventState.hasKickedOff){
            Logger.debug("First PLAY_ON, starting kick off behaviours")
            eventState.hasKickedOff = true

            // fun testing code!
            // move to four corners of field using MoveToPoint
//            val coords = listOf(Vector2(-50.0, 32.0), Vector2(-50.0, -32.0), Vector2(45.0, -32.0),
//                    Vector2(51.36, 32.31))
//            for (coord in coords){
//                behaviourManager.movementQueue.add(MoveToPoint(coord, 100.0))
//                behaviourManager.movementQueue.add(Sit(10000))
//            }

            // face increments of 90 degrees randomly
            val angles = mutableListOf(90.0, 180.0, 270.0, 0.0)
            angles.addAll(angles.reversed())
            for (angle in angles){
                movementManager.queue.add(TurnBodyTo(angle.toRadians()))
            }
            movementManager.queue.add(TurnBodyTo(0.0))
            movementManager.queue.add(Wait(2000))

//            // move around in the centre using FollowPath
//            val coords = arrayOf(Vector2(-7.0, -6.0), Vector2(-6.0, -6.0), Vector2(5.0, -7.0),
//                    Vector2(7.0, 5.0), Vector2(-7.0, -6.0))
//            val stamina = DoubleArray(coords.size) { 100.0 }
//            behaviourManager.movementQueue.add(FollowPath(coords, stamina))
        }
    }

    override fun handleInitMessage(init: IncomingInitMessage){
        if (init.playMode != PlayMode.BEFORE_KICK_OFF){
            Logger.warn("Unexpected play mode during init: ${init.playMode} (agent joined late?)")
            onPlayModeChange(init.playMode)
        }

        // first, we update our world models
        lowModel.selfUnum = init.unum
        lowModel.selfSide = init.side

        highModel.selfId = init.unum - 1 // note: id is zero indexed, unum is one indexed
        highModel.selfSide = init.side
        highModel.teamPlayers[highModel.selfId].isSelf = true
        highModel.teamPlayers[highModel.selfId].isGoalie = isGoalie
        Logger.info("Init message received: $init (self ID: ${highModel.selfId})")

        // info: the server (for the move command) actually considers right-side
        // coordinates as exactly the same as left side coordinates. So, instead of moving to (20, 0) for example,
        // the ACTUAL position... we move to (-20, 0) - the same as the left side. This means that no coordinate
        // adjustment is actually required.
        // once we have our unum, load the specified formation and move to our position
        val pos = startingFormation.getPosition(highModel.selfId)
        Logger.debug("Moving to initial position $pos for formation ${startingFormation.name}")
        pushBatch(MoveMessage(pos))
        // usually we won't listen to the opposition - can be changed in YAML config though
        pushBatch(EarMessage(status = CURRENT_CONFIG.get().listenToOpposition, us = false))
        // send synch_see which disables low quality mode
        // this is because most teams do this, and also I literally cannot find any use for low quality vision,
        // it is objectively trash and doesn't work with the localiser
        pushBatch(SyncSeeMessage())
        flushBatch()
    }

    override fun handleSeeMessage(see: SeeMessage){
        // first, setup our low level world model
        val flags = see.objects.filter { it.type == ObjectType.FLAG }
        // good flags are the non-weird ones we will use to localise with
        lowModel.goodFlags = flags.filter {
            it.name.isNotEmpty() && !it.isBehind && it.direction != null && it.distance != null
        }
        lowModel.allFlags = flags
        lowModel.players = see.objects.filter { it.type == ObjectType.PLAYER }
        lowModel.ball = see.objects.firstOrNull { it.type == ObjectType.BALL }
        lowModel.updateTime(see.time)
        Logger.trace("Received see message: $see")

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

                // TODO calculate velocity if distChange/dirChange is available
                if (info?.unum != null && info.teamName != null){
                    // we have lots of information: we can find out this player's id, and which team they're on
                    val id = info.unum!! - 1
                    val isOurSide = info.teamName == CURRENT_CONFIG.get().teamName
                    val array = if (isOurSide) highModel.teamPlayers else highModel.opponentPlayers

                    // note that we use ID here instead of unum since teamPlayers/opponentPlayers is zero indexed
                    array[id].isKnown = true
                    array[id].lastSeen = lowModel.time
                    array[id].isGoalie = info.goalie
                    val absolute = calculateAbsolutePosition(dir, dist, agentTransform)
                    // calculate body orientation in radians if available
                    val bodyDirection = calcBodyFaceDir(player.bodyFaceDir)
                    array[id].transform = Transform2D(absolute, bodyDirection)

                } else if (info?.unum != null){
                    // we know this player's id, we might be able to infer which team they're on
                    // TODO work out a way to infer team for certain players
                    // we could even calculate chances of which player is which (e.g. 50-50 chance it's id 2 or 3)
                    val absolute = calculateAbsolutePosition(dir, dist, agentTransform)
                    val bodyDirection = calcBodyFaceDir(player.bodyFaceDir)
                    val transform = Transform2D(absolute, bodyDirection)

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
                    // body orientation is very likely NOT available, but try anyways
                    val bodyDirection = calcBodyFaceDir(player.bodyFaceDir)
                    val transform = Transform2D(absolute, bodyDirection)

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
                    // here we may want to ask our teammates for help
                } else {
                    val absolute = calculateAbsolutePosition(ball.direction!!, ball.distance!!, agentTransform)
                    highModel.ball.lastPos.set(highModel.ball.pos)
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
            // because we couldn't localise, this means that the positions of ALL of our localised objects are
            // now unknown. so go and update them here
            for (i in highModel.teamPlayers.indices){
                highModel.teamPlayers[i].isKnown = false
                highModel.opponentPlayers[i].isKnown = false
            }
            // we don't clear out unknown player data if we have no flags, since we might want to work with it later
            for (i in highModel.unknownTeamPlayers.indices){
                highModel.unknownTeamPlayers[i].isKnown = false
            }
            for (i in highModel.unknownPlayers.indices){
                highModel.unknownPlayers[i].isKnown = false
            }
            highModel.ball.isKnown = false

            AGENT_STATS.get().failedLocalisations++
        }

        AGENT_STATS.get().goodFlagsRate.addValue(lowModel.goodFlags.size.toDouble() / lowModel.allFlags.size.toDouble())
    }

    override fun handleHearMessage(hear: HearMessage){
        Logger.debug("Received hear message: $hear")
        lowModel.updateTime(hear.time)
        val ourCoach = if (highModel.selfSide == Side.LEFT) MessageSender.ONLINE_COACH_LEFT else MessageSender.ONLINE_COACH_RIGHT

        when {
            hear.sender == MessageSender.REFEREE -> {
                // first check if the message is a play mode change
                val playModes = PlayMode.values().map { it.toString() }

                if (hear.message.toUpperCase() in playModes){
                    val newPlayMode = PlayMode.valueOf(hear.message.toUpperCase())
                    Logger.debug("Referee changing play mode to: $newPlayMode")
                    onPlayModeChange(newPlayMode)
                }
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

    override fun handleAnyNonErrorMessage() {
        // decrease our error count, don't set to zero in case we get spaced out errors
        // (we still need to do the emergency exit then)
        if (errorCount > 0){
            errorCount--
        }
    }

    override fun handleWarningMessage(warning: WarningMessage) {
        Logger.warn("Received server warning: ${warning.message}")
    }

    override fun teardown() {
        println("PlayerAgent teardown() running")
        thinkTimer.shutdownNow()
        thinkTimer.awaitTermination(255, TimeUnit.MILLISECONDS)
    }

    /**
     * Calculates the body face direction of a player from the see message.
     * If faceDir is null, returns -1.0 since it is therefore not known.
     * TODO check correctness.
     */
    private fun calcBodyFaceDir(faceDir: Int?): Double {
        return if (faceDir != null){
            ((faceDir + 360.0) % 360.0) * DEG_RAD
        } else {
            -1.0
        }
    }

    companion object {
        // similar to: https://stackoverflow.com/a/9748697/5007892
        private val namedThreadFactory = BasicThreadFactory.Builder()
            .namingPattern("Think-%d")
            .daemon(true)
            .build()
    }
}