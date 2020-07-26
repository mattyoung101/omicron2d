package io.github.omicron2d.communication.messages

import io.github.omicron2d.SERVER_PROTOCOL_VERSION
import org.parboiled.BaseParser
import org.parboiled.annotations.BuildParseTree

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