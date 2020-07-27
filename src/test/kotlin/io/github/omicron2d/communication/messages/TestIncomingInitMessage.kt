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

class TestIncomingInitMessage {
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

        assertEquals(msg, shouldBe)
    }

    @Test
    fun testDeserialisePerformance(){
        val time = measureTimeMillis {
            for (i in 0 until MESSAGE_DESERIALISATION_COUNT) {
                // generate a semi random message
                val testInitMessage = "(init ${if (Random.nextBoolean()) "l" else "r"} ${Random.nextInt(1, 11)} goal_kick_l)"

                val msg = IncomingInitMessage.deserialise(testInitMessage)
                println(msg.playMode)
            }
        } / MESSAGE_DESERIALISATION_COUNT

        // give it 35 ms tolerance (should be fine on most computers)
        assertTrue(time <= MESSAGE_DESERIALISATION_TIME)
        println("Average SimpleInitMessage deserialise time: $time ms")
    }

    @Test
    fun testDeserialiseError(){
        // TODO, basically want to check that bad messages don't parse
    }
}