package com.omicron.sim2d.communication.messages

import de.tudresden.inf.lat.jsexp.Sexp

/**
 * Interface for all deserialised messages from the soccer simulation server.
 */
interface SimulationMessage {
    /**
     * Deserialises the given S-expression to the _current_ instance of this Message.
     * @param root the root node of the cleaned S-expression. For example, message name and robot unum are removed.
     */
    fun deserialise(root: Sexp)

    /**
     * Serialises this message to an S-expression for transmission to the soccer server.
     */
    fun serialise(): String
}