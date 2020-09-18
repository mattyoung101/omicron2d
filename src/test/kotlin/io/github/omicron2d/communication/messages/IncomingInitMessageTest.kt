/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import io.github.omicron2d.MESSAGE_DESERIALISATION_COUNT
import io.github.omicron2d.MESSAGE_DESERIALISATION_TIME
import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.Side
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class IncomingInitMessageTest {
    @Test
    fun testBasic(){
        val msg1 = IncomingInitMessage(side = Side.LEFT, unum = 12, playMode = PlayMode.PLAY_ON)

        assertEquals(msg1.side, Side.LEFT)
        assertEquals(msg1.unum, 12)

        val copy = msg1.copy()
        assertEquals(msg1, copy)
    }

    @Test
    fun testDeserialisation(){
        val string = "(init l 5 goal_kick_l)"
        val msg = IncomingInitMessage.deserialise(string)
        val shouldBe = IncomingInitMessage(Side.LEFT, 5, PlayMode.GOAL_KICK_L)

        assertEquals(shouldBe, msg)
    }

    @Test
    fun testDeserialisePerformance(){
        val time = measureTimeMillis {
            for (i in 0 until MESSAGE_DESERIALISATION_COUNT) {
                // generate a semi random message
                val testInitMessage = "(init ${if (Random.nextBoolean()) "l" else "r"} ${Random.nextInt(1, 11)} goal_kick_l)"

                val msg = IncomingInitMessage.deserialise(testInitMessage)
            }
        }.toDouble() / MESSAGE_DESERIALISATION_COUNT

        assertTrue(time <= MESSAGE_DESERIALISATION_TIME)
        println("Average SimpleInitMessage deserialise time: $time ms")
    }

    @Test(expected = MessageParseException::class)
    fun testDeserialiseError(){
        val string = "(init INVALID INVALID BAD BAD"
        IncomingInitMessage.deserialise(string)
    }
}