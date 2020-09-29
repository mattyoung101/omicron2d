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
import java.util.concurrent.TimeUnit

/**
 * Class for all standard soccer agents (players) including the goalie.
 *
 * This is the class in which the main information processing pipeline takes place.
 */
class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port), PlayerMessageHandler {
    private val lowModel = LowLevelWorldModel()
    private val highModel = HighLevelWorldModel()

    override fun run() {
        Logger.debug("PlayerAgent main loop started")
        MarkerManager.refreshMarkers()

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
            // algorithm.
            // First we convert our messy flag data into polar flag observations that the localiser requires
            val observations = hashMapOf<String, FlagObservationPolar>()
            for (flag in lowModel.flags){
                observations[flag.name] = FlagObservationPolar(flag.distance, flag.direction.toDouble())
            }
            // and now we perform localisation
            val agentPos = ICPLocalisation.localise(observations)

            // Next of all, we need to update the high level world model with the absolute positions of all the objects
            // we observed relatively in the low level world model
        }
    }

    override fun handleInitMessage(init: IncomingInitMessage){
        if (init.playMode != PlayMode.BEFORE_KICK_OFF){
            Logger.warn("Unexpected play mode during init: ${init.playMode}")
        }
        lowModel.side = init.side
        lowModel.unum = init.unum
        Logger.debug("Init message received: $init")
    }

    override fun handleSeeMessage(see: SeeMessage){
        // filter out only flags in the see message
        lowModel.flags = see.objects.filter { it.type == ObjectType.FLAG }
        lowModel.players = see.objects.filter { it.type == ObjectType.PLAYER }
        lowModel.ball = see.objects.firstOrNull { it.type == ObjectType.BALL }
        Logger.debug("Received see message: $see")
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