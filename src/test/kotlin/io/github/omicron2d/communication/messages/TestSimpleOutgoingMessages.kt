package io.github.omicron2d.communication.messages

import org.junit.Assert.*
import org.junit.Test

class TestOutgoingInitMessage {
    @Test
    fun testBasic(){
        val msg1 = OutgoingInitMessage(teamName = "Test123", version = "15", isGoalie = false)
        val msg2 = OutgoingInitMessage(teamName = "Test321", version = "15", isGoalie = true)

        assertEquals(msg1.teamName, "Test123")
        assertEquals(msg1.version, "15")
        assertFalse(msg1.isGoalie)
        assertTrue(msg2.isGoalie)
    }

    @Test
    fun testSerialisation(){
        val msg1 = OutgoingInitMessage(teamName = "Test123", version = "15", isGoalie = false)
        val msg2 = OutgoingInitMessage(teamName = "Test321", version = "15", isGoalie = true)

        assertEquals(msg1.serialise(), "(init Test123 (version 15))")
        assertEquals(msg2.serialise(), "(init Test321 (version 15) (goalie))")
    }
}