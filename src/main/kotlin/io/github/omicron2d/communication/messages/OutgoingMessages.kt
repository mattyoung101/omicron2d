/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION
import io.github.omicron2d.utils.ViewMode
import io.github.omicron2d.utils.ViewQuality
import java.text.DecimalFormat

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15
// Note: for most of the floats before, I would string format using "%.2f" but according to the logs I've looked at,
// the server supports weird formats like even "1e+06" so we should be fine to send that

// https://stackoverflow.com/a/2538798/5007892
private val fmt = DecimalFormat().apply {
    minimumFractionDigits = 1
    maximumFractionDigits = 8
}

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
        return "(catch $direction)"
    }
}

/**
 * Note that in synch_see mode, which is enabled, high quality vision is the only possible mode. Changing to low
 * quality will probably cause a server error.
 */
data class ChangeViewMessage(var mode: ViewMode = ViewMode.UNKNOWN,
                             val quality: ViewQuality = ViewQuality.HIGH) : OutgoingServerMessage {
    override fun serialise(): String {
        return "(change_view ${mode.toString().toLowerCase()} ${quality.toString().toLowerCase()})"
    }
}

/**
 * @param power >0-100
 * @param direction -180 to 180
 */
data class DashMessage(var power: Double = 0.0, var direction: Double? = null): OutgoingServerMessage {
    override fun serialise(): String {
        return if (direction != null){
            "(dash ${fmt.format(power)} ${fmt.format(direction)})"
        } else {
            "(dash ${fmt.format(power)})"
        }
    }
}

/**
 * @param angle -180 to 180
 */
data class TurnMessage(var angle: Double = 0.0): OutgoingServerMessage {
    override fun serialise(): String {
        return "(turn ${fmt.format(angle)})"
    }
}

/** Move message, for use during initial setup */
data class MoveMessage(var x: Double = 0.0, var y: Double = 0.0): OutgoingServerMessage {
    constructor(pos: Vector2) : this(pos.x, pos.y)

    override fun serialise(): String {
        return "(move $x $y)"
    }
}

/**
 * @param status true if on, false if off
 * @param us true if us, false if opposition
 */
data class EarMessage(val status: Boolean, val us: Boolean): OutgoingServerMessage {
    override fun serialise(): String {
        return "(ear (${if (status) "on" else "off"} ${if (us) "our" else "opp"}))"
    }
}

/** Note: server uses the spelling "synch" instead. */
class SyncSeeMessage: OutgoingServerMessage {
    override fun serialise(): String {
        return "(synch_see)"
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