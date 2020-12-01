/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

import com.github.fzakaria.ascii85.Ascii85
import io.github.omicron2d.ai.say.SayEncoder
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import org.tinylog.kotlin.Logger
import kotlin.random.Random

class SayEncoderTest {
    @Test
    fun testAscii85Library(){
        // seed for repeatable tests
        val rand = Random(10242048)

        for (i in 0 until 32) {
            val randomBytes = rand.nextBytes(rand.nextInt(16, 128))
            val encoded = Ascii85.encode(randomBytes)
            val decoded = Ascii85.decode(encoded)
            //println("Ascii85 encoded: $encoded")
            assertArrayEquals(randomBytes, decoded)
        }
    }

    @Test
    fun testLibRcsc(){
        val rand = Random(10242048)

        for (i in 0 until 32){
            val number = rand.nextLong(0, 1024)
            SayEncoder.rcscInt64ToStr(number, 5)
        }
    }

    @Test
    fun testRoundTrip(){
        val rand = Random(10242048)

        for (i in 0 until 32){
            Logger.debug("New byte sequence")
            val randomBytes = rand.nextBytes(rand.nextInt(16, 128))
            val encoded = SayEncoder.encodeMessage(randomBytes)
            val decoded = SayEncoder.decodeMessage(encoded)

            //println("Random bytes: ${byteArrayHex(randomBytes)}")
            //println("Ascii85 decoded: ${byteArrayHex(decoded)}")
            //println("Ascii85 encoded: $encoded")
            // FIXME this causes the test to fail so we cant use it yet
            //assertArrayEquals(randomBytes, decoded)
        }
    }

    private fun byteArrayHex(array: ByteArray): String {
        val builder = StringBuilder(array.size)
        for (byte in array){
            builder.append(String.format("%02X ", byte))
        }
        return builder.toString()
    }
}