package io.github.omicron2d.communication.messages

/**
 * Base class for all messages that can be serialised to, or deserialised from the soccer server
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
    // this class has no methods, instead see IncomingMessageDeserialiser which they are all expected to implement
}

/**
 * Interface for all companion objects of IncomingServerMessage classes that can deserialise server S-expressions
 */
interface IncomingMessageDeserialiser {
    /**
     * Deserialises the given S-expression and returns a new instance of the respective IncomingServerMessage
     * @param input the S-expression, as pretty much received directly from the server
     */
    fun deserialise(input: String): IncomingServerMessage
}