/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15

/**
 * Client to server init message
 */
data class OutgoingInitMessage(var teamName: String = "", var version: String = SERVER_PROTOCOL_VERSION,
                               var isGoalie: Boolean = false) : OutgoingServerMessage {
    override fun serialise(): String {
        return "(init $teamName (version $version)${if (isGoalie) " (goalie)" else ""})"
    }
}