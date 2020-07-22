package io.github.omicron2d.communication.messages

import de.tudresden.inf.lat.jsexp.Sexp

/**
 * Base class for all messages that can be serialised and/or deserialised to the soccer server
 */
interface ServerMessage

/**
 * Outgoing server message: transmitted from the client to the server
 */
interface OutgoingServerMessage : ServerMessage {
    /**
     * Serialises this message to an S-expression for transmission to the soccer server.
     */
    fun serialise(): String
}

/**
 * Incoming server message: transmitted from the server to the client
 */
interface IncomingServerMessage : ServerMessage {
    /**
     * Deserialises the given S-expression to the _current_ instance of this Message.
     * @param root the root node of the full parsed S-expression.
     */
    fun deserialise(root: Sexp)
}