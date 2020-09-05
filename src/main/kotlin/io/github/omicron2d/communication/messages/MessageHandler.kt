/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

/**
 * A MessageHandler is a class that Agents (those who inherit from SoccerAgent) can use to deserialise messages and
 * call the appropriate handler function for the deserialised message
 */
interface MessageHandler {
    fun dispatchMessage(msg: String)
}

/**
 * Message handler for player agents
 */
interface PlayerMessageHandler : MessageHandler {

}