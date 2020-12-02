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
import io.github.omicron2d.utils.MessageSender
import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.SAY_CHARSET
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class HearMessageTest {
    @Test
    fun testBasic(){
        val msg = HearMessage(time = 35, sender = null, direction = -35.0, message = "TEST")
        assertEquals(msg.time, 35)
        assertNull(msg.sender)
        assertEquals(msg.direction, -35.0)
        assertEquals(msg.message, "TEST")
    }

    @Test
    fun testSimpleDeserialisation() {
        val testHearMessage = "(hear 34 online_coach_left \"testing**???123456__<<>>\")"
        val msg = HearMessage.deserialise(testHearMessage)
        val shouldBe = HearMessage(time = 34, sender = MessageSender.ONLINE_COACH_LEFT, message = "testing**???123456__<<>>")
        assertEquals(shouldBe, msg)

        val tm2 = "(hear 34 -1 \"test\")"
        val msg2 = HearMessage.deserialise(tm2)
        assertEquals(-1.0, msg2.direction)
        assertNull(msg2.sender)
    }

    @Test
    fun testRefMessage(){
        // sample of real ref calls to make sure it can parse those
        val msg = HearMessage.deserialise("(hear 0 referee drop_ball)")
        val msg2 = HearMessage.deserialise("(hear 137 referee yellow_card_l_1)")

        assertEquals(MessageSender.REFEREE, msg.sender)
        assertEquals("drop_ball", msg.message)
        assertEquals("yellow_card_l_1", msg2.message)
    }

    @Test(expected = MessageParseException::class)
    fun testParseError(){
        val msg = "(hear 129837 -1.0.0.0.0 \"invali!!!d)"
        HearMessage.deserialise(msg)
    }

    private fun generateTestMessages(): List<String>{
        val testMessages = mutableListOf<String>()

        for (i in 0 until MESSAGE_DESERIALISATION_COUNT){
            val msgStr = SAY_CHARSET.toList().shuffled().take(Random.nextInt(1, 10)).joinToString("")
            val time = Random.nextInt(0, 1000)

            if (Random.nextBoolean()) {
                // player or coach message
                if (Random.nextBoolean()) {
                    // use sender, removing MessageSender.UNKNOWN since the parser doesn't support that
                    val sender = MessageSender.values().filter { it != MessageSender.UNKNOWN }.random()
                    testMessages.add("(hear $time ${sender.toString().toLowerCase()} \"$msgStr\")")
                } else {
                    // use direction
                    val direction = Random.nextDouble(-180.0, 180.0)
                    testMessages.add("(hear $time $direction \"$msgStr\")")
                }
            } else {
                // referee message
                val randomPlayMode = PlayMode.values().random()
                testMessages.add("(hear ${Random.nextInt(0, 1000)} referee ${randomPlayMode.toString().toLowerCase()})")
            }
        }

        return testMessages
    }

    @Test
    fun testPerformanceAndRandom(){
        val testMessages = generateTestMessages()

        // time deserialisation
        val time = measureTimeMillis {
            for (entry in testMessages){
                val result = HearMessage.deserialise(entry)
                //println(result) // do something to prevent optimisation
            }
        }.toDouble() / MESSAGE_DESERIALISATION_COUNT

        // check the time to deserialise
        println("Average HearMessage deserialise time: $time ms")
        assertTrue(time <= MESSAGE_DESERIALISATION_TIME)
    }
}