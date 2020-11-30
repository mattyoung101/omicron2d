/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import io.github.omicron2d.communication.AbstractSoccerAgent
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import java.net.InetAddress

/**
 * This agent connects to the server, then immediately disconnects. It can be used to "bags" the left side of the field,
 * for testing what happens when we join the right side.
 */
class UselessAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port) {
    override fun run() {
        println("Joined server. Doing nothing.")
        return
    }

    companion object {
        /**
         * Starts the useless agent and does nothing.
         */
        fun launch(){
            val initMessage = OutgoingInitMessage("Useless2D", SERVER_PROTOCOL_VERSION, false)
            val agent = UselessAgent()
            agent.connect(initMessage, true)
            println("Done nothing successfully.")
        }
    }
}

fun main(args: Array<String>){
    UselessAgent.launch()
}