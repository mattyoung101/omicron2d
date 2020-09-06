/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication

import io.github.omicron2d.ai.world.HighLevelWorldModel
import io.github.omicron2d.ai.world.LowLevelWorldModel
import io.github.omicron2d.communication.messages.*
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
class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = 6000) : SoccerAgent(host, port), PlayerMessageHandler {
    private val lowModel = LowLevelWorldModel()
    private val highModel = HighLevelWorldModel()

    override fun run() {
        Logger.debug("PlayerAgent main loop started")

        while (true){
            // 1. Receive message from server and parse
            val msgStr = messages.poll(30, TimeUnit.SECONDS)
            if (msgStr == null){
                Logger.warn("Unexpected null message from message queue, server dead? Terminating!")
                break
            } else if (msgStr == "INTERNAL_TIMED_OUT"){
                Logger.warn("Received server timeout message, terminating PlayerAgent!")
                break
            }

            // 2. Dispatch message to handlers
            dispatchMessage(msgStr)

            // 3. We have now created the low level world model. Perform localisation on high level world model.

            // 4. Update positions of seen objects in high level world model
            //transmitString("(move 1 1)")
        }
    }

    override fun handleInitMessage(init: IncomingInitMessage){
        if (init.playMode != PlayMode.BEFORE_KICK_OFF){
            Logger.warn("Unexpected play mode during init: ${init.playMode}")
        }
        lowModel.side = init.side
        lowModel.unum = init.unum
        Logger.debug("Parsed init message $init, world model is now $lowModel")
    }

    override fun handleSeeMessage(see: SeeMessage){
        // filter out only flags in the see message
        lowModel.flags = see.objects.filter { it.type == ObjectType.FLAG }
        lowModel.players = see.objects.filter { it.type == ObjectType.PLAYER }
        lowModel.ball = see.objects.firstOrNull { it.type == ObjectType.BALL }
    }

    override fun handleHearMessage(hear: HearMessage){

    }

    override fun handleErrorMessage(error: ErrorMessage){
        Logger.warn("Received server error: ${error.message}")
    }
}