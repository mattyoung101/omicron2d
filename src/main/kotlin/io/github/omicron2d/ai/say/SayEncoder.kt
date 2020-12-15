/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.say

import com.github.fzakaria.ascii85.Ascii85
import io.github.omicron2d.utils.SAY_CHARSET
import org.tinylog.kotlin.Logger

/**
 * Encodes messages to be sent with the `say` command.
 *
 * So here's my idea for this. Basically, we use Ascii85 (more efficient than Base64), then, for characters which we
 * are not allowed to include in the say message, we use double characters. For example, `#` might be come `++`.
 * This is sort of similar to byte pair encoding maybe? Or escaping?
 *
 * The messages themselves could be anything, maybe Protobuf? with some sort of compression too?
 */
object SayEncoder {
    /**
     * Charset of the Adobe variant of Base85, as used in the library we use to encode Base85.
     * Reference: [https://en.wikipedia.org/wiki/Ascii85#Adobe_version]
     *
     * Run this Python code to list all chars: `print("".join([chr(x) for x in range(33, 117)]))`
     */
    private const val ASCII85_CHARSET = "!\"#\$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrst"
    /** List of chars that are only in Base85 and not the say charset */
    private val ASCII85_ONLY_CHARS = ASCII85_CHARSET.toSet().subtract(SAY_CHARSET.toSet())
    /** Each char in [ASCII85_ONLY_CHARS] has a double char replacement in this map for sending with the `say` command. */
    private val REPLACEMENTS = mutableMapOf<String, String>()
    /** Character used to escape replacements that appear naturally in the Ascii85 message */
    private const val ESCAPE_CHAR = "\$\$ESCAPE\$\$"

    init {
        // initialise the replacements map
        Logger.debug("Initialising replacements...")
        for ((i, char) in ASCII85_ONLY_CHARS.withIndex()){
            val sayChar = SAY_CHARSET[i]
            REPLACEMENTS[char.toString()] = "$sayChar$sayChar"
            Logger.debug("$char -> $sayChar$sayChar")
        }
    }

    // I was lazy and dumb so this is borrowed from: https://www.baeldung.com/java-indexOf-find-string-occurrences
    // Performance shouldn't be terrible for messages of our length
    private fun findAllSubstrings(word: String, string: String): List<Int> {
        val out = mutableListOf<Int>()
        var index = 0
        while (index != -1) {
            index = string.indexOf(word, index)
            if (index != -1) {
                out.add(index)
                index++
            }
        }
        return out
    }

    /*
     * References for message framing (will need to implement this):
     * - https://eli.thegreenplace.net/2009/08/12/framing-in-serial-communications
     * - http://www.ece.ubc.ca/~edc/3525.jan2015/lectures/lec8.pdf
     * - https://www.net.t-labs.tu-berlin.de/teaching/computer_networking/05.08.htm
     */

    /**
     * Encodes a byte array to a series of characters that can be sent using the say command.
     * Works by using Ascii85 and substituting out non-say charset characters with double characters of ones in the say
     * charset.
     */
    fun encodeMessage(bytes: ByteArray): String {
        var msg = Ascii85.encode(bytes)
        // first, we go through the message, and if there is already a replacement character
        Logger.debug("Message before: $msg")

        for (value in REPLACEMENTS.values){
            Logger.debug("Checking for replacement $value")
            // value is the double char sequence thingy we want to escape
            val indices = findAllSubstrings(value, msg)
            for (index in indices){
                Logger.debug("FOUND occurrence at $index")
                // splice in our character, we get from begin to current char, then add our char, then that to the end
                msg = msg.substring(0, index) + ESCAPE_CHAR + msg.substring(index, msg.length)
            }
        }
        Logger.debug("Message after: $msg")

        // substitute out special chars with replacements for sending with say message
        for ((key, value) in REPLACEMENTS){
            msg = msg.replace(key, value)
        }
        Logger.debug("Final message: $msg")
        return msg
    }

    /**
     * Decodes a say message from our team to a byte array. First we substitute back in the original chars from the
     * replacements, then just pass it off to the Ascii85 decoder
     */
    fun decodeMessage(inMsg: String): ByteArray {
        var msg = inMsg
        // basically do the above, but in reverse, putting our replacements back to the original chars
        for ((key, value) in REPLACEMENTS){
            msg = msg.replace(value, key)
        }
        return Ascii85.decode(msg)
    }

    /**
     * Method that librcsc uses to encode int64 to str:
     * `AudioCodec::encodeInt64ToStr`, line 102 of common/audio_codec.cpp
     */
    fun rcscInt64ToStr(number: Long, len: Int): String {
        val charSet = SAY_CHARSET
        val charSize = SAY_CHARSET.length
        var out = ""

        val remainderValues = mutableListOf<Long>()
        var divided = number
        for (i in 0 until len - 1){
            remainderValues.add(divided % charSize)
            divided /= charSize
        }

        if (divided >= charSize){
            throw IllegalArgumentException("Illegal value")
        }
        remainderValues.add(divided)

        try {
            for (value in remainderValues){
                out += charSet[value.toInt()]
            }
        } catch (e: Exception){
            throw IllegalArgumentException(e)
        }

        Logger.debug("Encoded number $number length $len to: $out")
        return out
    }
}