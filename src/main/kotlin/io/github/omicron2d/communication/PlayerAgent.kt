/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication

import java.net.InetAddress

class PlayerAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = 6000) : SoccerAgent(host, port) {
    private var lastMessageTime = System.currentTimeMillis()

    override fun onReceiveMessage(message: String) {
        // we would go and parse it here

        // TODO read:
        // so the way this works, is that PlayerAgent should run on the main thread
        // we will use a LinkedBlockingQueue which the network thread will post to, then we wait for it here

        println("(last: ${System.currentTimeMillis() - lastMessageTime}) $message")
        lastMessageTime = System.currentTimeMillis()
    }

    // make event loop shit here with queue
    // also consider making all the queue stuff in the SoccerAgent, abstract it out
    // TODO consider using event bus????
    // hash map of parsers?
}