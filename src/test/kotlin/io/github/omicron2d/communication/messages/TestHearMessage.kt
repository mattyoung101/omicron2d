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

class TestHearMessage {
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
        assertEquals(msg, shouldBe)

        val tm2 = "(hear 34 -1 \"test\")"
        val msg2 = HearMessage.deserialise(tm2)
        assertEquals(msg2.direction, -1.0)
        assertNull(msg2.sender)
    }

    @Test
    fun testRefMessage(){
        // sample of real ref calls to make sure it can parse those
        val msg = HearMessage.deserialise("(hear 0 referee drop_ball)")
        val msg2 = HearMessage.deserialise("(hear 137 referee yellow_card_l_1)")

        assertEquals(msg.sender, MessageSender.REFEREE)
        assertEquals(msg.message, "drop_ball")
    }

    @Test(expected = MessageParseException::class)
    fun testParseError(){
        val msg = "(hear 129837 -1.0.0.0.0 \"invalid)"
        HearMessage.deserialise(msg)
    }

    @Test
    fun testPerformanceAndRandom(){
        // setup test
        val testMessages = mutableListOf<String>()
        for (i in 0 until MESSAGE_DESERIALISATION_COUNT){
            val msgStr = SAY_CHARSET.toList().shuffled().take(Random.nextInt(1, 10)).joinToString("")
            val time = Random.nextInt(0, 1000)

            if (Random.nextBoolean()) {
                // player or coach message
                if (Random.nextBoolean()) {
                    // use sender
                    val sender = MessageSender.values().random()
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
        println("TEST MESSAGES:")
        println(testMessages.joinToString("\n"))

        // time deserialisation
        val time = measureTimeMillis {
            for (entry in testMessages){
                val result = HearMessage.deserialise(entry)
            }
        } / MESSAGE_DESERIALISATION_COUNT

        // give it 35 ms tolerance (should be fine on most computers)
        println("Average IncomingHearMessage deserialise time: $time ms")
        assertTrue(time <= MESSAGE_DESERIALISATION_TIME)
    }
}