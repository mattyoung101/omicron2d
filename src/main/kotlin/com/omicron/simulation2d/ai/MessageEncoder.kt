package com.omicron.simulation2d.ai

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Output
import com.omicron.simulation2d.Message
import com.omicron.simulation2d.Messages
import java.util.*

/**
 * The MessageEncoder will encode a class into a
 */
class MessageEncoder(private val kryo: Kryo) {
    private val output = Output(64).apply {
        variableLengthEncoding = true
    }
    private val base64 = Base64.getEncoder()

    fun encode(msg: Message): String {
        output.reset()
        kryo.writeObject(output, msg)

        val bytes = output.buffer.take(output.total().toInt())

//        Logger.trace("Written ${output.total()} bytes, encoded: $encoded (${encoded.length} chars)")
        return base64.encodeToString(bytes.toTypedArray().toByteArray()) // TODO these conversions are bad for RAM
    }
}