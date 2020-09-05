/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

@Suppress("MoveVariableDeclarationIntoWhen")
object MessageFactory {
    /**
     * Parses an incoming server message and returns the deserialised IncomingSeverMessage implementation, or null if
     * the message has no deserialiser. May throw a MessageParseException if the message is malformed.
     */
    fun parseIncomingMessage(msg: String): IncomingServerMessage? {
        val name = msg.split(" ").first().replace("(", "")

        return when (name) {
            "init" -> IncomingInitMessage.deserialise(msg)
            "hear" -> HearMessage.deserialise(msg)
            "see" -> SeeMessage.deserialise(msg)
            "error" -> ErrorMessage.deserialise(msg)
            else -> null
        }
    }
}