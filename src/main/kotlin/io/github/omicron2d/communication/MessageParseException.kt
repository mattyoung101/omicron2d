package io.github.omicron2d.communication

/**
 * Indicates that an IncomingServerMessage could not be parsed and deserialised
 */
class MessageParseException(reason: String = "") : RuntimeException(reason) {
}