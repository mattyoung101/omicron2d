package com.omicron.sim2d.communication.messages

import com.omicron.sim2d.PlayMode
import com.omicron.sim2d.SERVER_PROTOCOL_VERSION
import com.omicron.sim2d.Side
import de.tudresden.inf.lat.jsexp.Sexp
import org.tinylog.kotlin.Logger
import java.lang.IllegalArgumentException

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15

/**
 * Server to client init message
 */
data class IncomingInitMessage(var side: Side, var unum: Int, var playMode: PlayMode) : IncomingServerMessage {
    override fun deserialise(root: Sexp) {
        this.side = if (root[1].toString() == "l") Side.LEFT else Side.RIGHT
        this.unum = root[2].toString().toInt()
        this.playMode = PlayMode.valueOf(root[3].toString().toUpperCase())
    }
}