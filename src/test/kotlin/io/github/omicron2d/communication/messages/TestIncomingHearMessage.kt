package io.github.omicron2d.communication.messages

import org.junit.Test

class TestIncomingHearMessage {
    @Test
    fun removeMeLater(){
        val testHearMessage = "(hear 34 -34 \"big meme?<<>>\")"
        val msg2 = IncomingHearMessage().deserialise(testHearMessage)
    }
}