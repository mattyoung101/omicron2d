package io.github.omicron2d.communication.messages

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15

/**
 * Server to client error message
 */
data class ErrorMessage(var message: String = "") : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): ErrorMessage {
            // simple and dumb parser
            return ErrorMessage(input.split(" ").last().replace(")", ""))
        }
    }
}