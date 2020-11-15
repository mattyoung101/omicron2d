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
import io.github.omicron2d.utils.ViewMode
import io.github.omicron2d.utils.ViewQuality

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15

/** Client to server init message */
data class OutgoingInitMessage(var teamName: String = "", var version: String = SERVER_PROTOCOL_VERSION,
                               var isGoalie: Boolean = false) : OutgoingServerMessage {
    override fun serialise(): String {
        return "(init $teamName (version $version)${if (isGoalie) " (goalie)" else ""})"
    }
}

/** Catch message for goalie */
data class CatchMessage(val direction: Double): OutgoingServerMessage {
    override fun serialise(): String {
        val str = "%.2f".format(direction)
        return "(catch $str)"
    }
}

data class ChangeViewMessage(var width: ViewMode = ViewMode.UNKNOWN,
                             val quality: ViewQuality = ViewQuality.UNKNOWN) : OutgoingServerMessage {
    override fun serialise(): String {
        return "(change_view ${width.toString().toLowerCase()} ${quality.toString().toLowerCase()})"
    }
}

data class DashMessage(var power: Double = 0.0): OutgoingServerMessage {
    override fun serialise(): String {
        val str = "%.2f".format(power)
        return "(dash $str)"
    }
}

/**
 * @param angle -180 to 180 (TODO check this)
 */
data class TurnMessage(var angle: Int = 0): OutgoingServerMessage {
    override fun serialise(): String {
        // TODO can angle be a double?
        return "(turn $angle)"
    }
}

/** Move message, for use during initial setup */
data class MoveMessage(var x: Double = 0.0, var y: Double = 0.0): OutgoingServerMessage {
    override fun serialise(): String {
        val xstr = "%.2f".format(x)
        val ystr = "%.2f".format(y)
        return "(move $xstr $ystr)"
    }
}

/**
 * @param angle -180 to 180
 */
data class TurnNeckMessage(var angle: Int = 0): OutgoingServerMessage {
    override fun serialise(): String {
        return "(turn_neck $angle)"
    }
}

data class SayMessage(var message: String = ""): OutgoingServerMessage {
    override fun serialise(): String {
        return "(say \"$message\")"
    }
}