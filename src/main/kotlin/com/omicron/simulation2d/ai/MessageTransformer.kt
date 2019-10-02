package com.omicron.simulation2d.ai

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.omicron.simulation2d.Message
import org.tinylog.kotlin.Logger
import java.util.*

/**
 * Turns a Message into a string for the say command, and vice versa
 */
class MessageTransformer(private val kryo: Kryo) {
    private val output = Output(256)
    private val input = Input(256)
    private val b64Encoder = Base64.getEncoder()
    private val b64Decoder = Base64.getDecoder()

    /** Serialises a message to a byte array and then converts it to a string with base64 **/
    fun encode(msg: Message): String {
        output.reset()
        kryo.writeObject(output, msg)
        val bytes = output.buffer.take(output.total().toInt()).toByteArray()
        return b64Encoder.encodeToString(bytes)
    }

    /** Turns a base64 string into a byte array then deserialises it into a Message object **/
    fun decode(msg: String): Message? {
        return try {
            val bytes = b64Decoder.decode(msg)
            input.reset()
            input.buffer.fill(0)
            for ((i, byte) in bytes.withIndex()){
                input.buffer[i] = byte
            }
            kryo.readObject(input, Message::class.java)
        } catch (e: Exception){
            Logger.warn("Malformed say input: $msg  ($e)")
            null
        }
    }
}