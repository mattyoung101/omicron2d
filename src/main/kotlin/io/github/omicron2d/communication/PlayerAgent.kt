/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication

import io.github.omicron2d.ai.world.*
import io.github.omicron2d.communication.messages.*
import io.github.omicron2d.utils.DEFAULT_PLAYER_PORT
import io.github.omicron2d.utils.ObjectType
import io.github.omicron2d.utils.PlayMode
import org.tinylog.kotlin.Logger
import java.net.InetAddress
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.random.Random

/**
 * Class for all standard soccer agents (players) including the goalie.
 *
 * This is the class in which the main information processing pipeline takes place.
 */
class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port), PlayerMessageHandler {
    private val lowModel = LowLevelWorldModel()
    private val highModel = HighLevelWorldModel()
    private var initial = false

    override fun run() {
        Logger.debug("PlayerAgent main loop started")

        // NOTE: just for testing
        timer(initialDelay=5000, period=300, action={
            //transmit(TurnMessage(10))
        })

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

            // NOTE: just for testing
            if (!initial){
                transmit(MoveMessage(-41.74, 0.23))
                initial = true
                Logger.debug("Initial")
            }

            // Dispatch message to handlers for data processing. Runs in same thread, no need to worry about data races
            dispatchMessage(msgStr)

            // With all of our data now processed in the LowLevelWorldModel, we now perform localisation using the ICP
            // algorithm.
            // First we convert our messy flag data into polar flag observations that the localiser requires
            if (lowModel.flags.isNotEmpty()) {
                val observations = hashMapOf<String, FlagObservationPolar>()
                for (flag in lowModel.flags) {
                    // convert angle from -180 to 180 (from server) to 0 to 360
                    val direction = (flag.direction.toDouble() + 360.0) % 360.0
                    observations[flag.name] = FlagObservationPolar(flag.distance, direction)
                }
                // and now we perform localisation
                val agentPos = ICPLocalisation.performLocalisation(observations)
            }

            // Next of all, we need to update the high level world model with the absolute positions of all the objects
            // we observed relatively in the low level world model
        }
    }

    override fun handleInitMessage(init: IncomingInitMessage){
        if (init.playMode != PlayMode.BEFORE_KICK_OFF){
            Logger.warn("Unexpected play mode during init: ${init.playMode} (agent joined late?)")
        }
        lowModel.side = init.side
        lowModel.unum = init.unum
        Logger.info("Init message received: $init")
    }

    override fun handleSeeMessage(see: SeeMessage){
        // filter out only flags in the see message
        lowModel.flags = see.objects.filter { it.type == ObjectType.FLAG }
        lowModel.players = see.objects.filter { it.type == ObjectType.PLAYER }
        lowModel.ball = see.objects.firstOrNull { it.type == ObjectType.BALL }
        Logger.trace("Received see message: $see")
    }

    override fun handleHearMessage(hear: HearMessage){
        Logger.debug("Received hear message: $hear")
    }

    override fun handleErrorMessage(error: ErrorMessage){
        Logger.warn("Received server error: ${error.message}")
    }

    override fun handleWarningMessage(warning: WarningMessage) {
        Logger.warn("Received server warning: ${warning.message}")
    }
}