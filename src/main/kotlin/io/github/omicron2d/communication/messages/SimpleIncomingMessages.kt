/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15

/**
 * Server to client error message
 */
data class ErrorMessage(var message: String = "") : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): ErrorMessage {
            // simple and dumb parser
            return ErrorMessage(input.split(" ").last().replace(")", ""))
        }
    }
}

/**
 * Server to client warning message
 */
data class WarningMessage(var message: String = "") : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): WarningMessage {
            // simple and dumb parser
            return WarningMessage(input.split(" ").last().replace(")", ""))
        }
    }
}