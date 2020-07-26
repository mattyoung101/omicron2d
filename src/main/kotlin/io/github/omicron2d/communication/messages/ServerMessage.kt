package io.github.omicron2d.communication.messages

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
     * @param input the S-expression, as pretty much received directly from the server
     */
    // TODO replace this with deserialise companion object
    fun deserialise(input: String)
}

/**
 * Interface for all companion objects of IncomingServerMessage classes that can deserialise server messages
 */
interface IncomingMessageDeserialiser {
    /**
     * Deserialises the given S-expression and returns a new instance of the respective IncomingServerMessage
     * @param input the S-expression, as pretty much received directly from the server
     */
    fun deserialise(input: String): IncomingServerMessage
}