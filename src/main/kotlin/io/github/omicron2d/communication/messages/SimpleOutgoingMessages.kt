package io.github.omicron2d.communication.messages

import io.github.omicron2d.SERVER_PROTOCOL_VERSION
import de.tudresden.inf.lat.jsexp.Sexp
import de.tudresden.inf.lat.jsexp.SexpFactory
import de.tudresden.inf.lat.jsexp.SexpList
import org.parboiled.BaseParser

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

    private open class Parser : BaseParser<Any>() {

    }
}