/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import com.google.gson.GsonBuilder
import io.github.omicron2d.utils.ObjectType
import org.junit.Assert.*
import org.junit.Test

class SeeMessageTest {
    @Test
    fun basicTest(){
        val msg = SeeMessage()

        val seeObject = SeeObject()
        seeObject.name = "test"
        seeObject.type = ObjectType.FLAG
        msg.objects.add(seeObject)

        assertEquals(msg.objects.size, 1)
        assertEquals(msg.objects[0].name, "test")
        assertEquals(msg.objects[0].type, ObjectType.FLAG)
        assertNull(msg.objects[0].playerInfo)
    }

    @Test
    fun basicDeserialiseTest(){
        val testMsg = "(see 0 ((f c t) 21.1 8 0 0) ((f r t) 73.7 2) ((f r b) 102.5 44) ((f g r b) 85.6 31) " +
                "((g r) 82.3 27) ((f g r t) 79 22) ((f p r c) 68 33) ((f p r t) 59.1 16) ((f t 0) 21.1 -5 0 0) " +
                "((f t r 10) 31.2 -4) ((f t r 20) 40.9 -3) ((f t r 30) 50.9 -2) ((f t r 40) 60.9 -2) " +
                "((f t r 50) 70.8 -2) ((f t l 10) 11.1 -10 0 0) ((f r 0) 86.5 25) " +
                "((f r t 10) 83.1 19) ((f r t 20) 80.6 12) ((f r t 30) 79 5) ((f r b 10) 91.8 31) " +
                "((f r b 20) 96.5 36) ((f r b 30) 103.5 40))"
        val deserialised = SeeMessage.deserialise(testMsg)
        for (entry in deserialised.objects){
            println(entry)
        }

        assertEquals(deserialised.objects.size, 22)
        // check to make sure at least one object is in there
        assertNotNull(deserialised.objects.firstOrNull {
            it.name == "f r b 20"
        })
        assertNotNull(deserialised.objects.firstOrNull {
            it.name == "f g r t"
        })
    }

    @Test
    fun testCorpusAndPerformance(){
        // here I would like to run it through an enormous corpus of see messages
    }

    @Test(expected = MessageParseException::class)
    fun errorTest(){
        val msg = "(see 0 ((f q z) --21.1 8 0 0)"
        SeeMessage.deserialise(msg)
    }

    // not currently a test, just interesting to note we can do this and it works pretty well
    fun testToJson(){
        val testMsg = "(see 0 ((f c t) 21.1 8 0 0) ((f r t) 73.7 2) ((f r b) 102.5 44) ((f g r b) 85.6 31) " +
                "((g r) 82.3 27) ((f g r t) 79 22) ((f p r c) 68 33) ((f p r t) 59.1 16) ((f t 0) 21.1 -5 0 0) " +
                "((f t r 10) 31.2 -4) ((f t r 20) 40.9 -3) ((f t r 30) 50.9 -2) ((f t r 40) 60.9 -2) " +
                "((f t r 50) 70.8 -2) ((f t l 10) 11.1 -10 0 0) ((f r 0) 86.5 25) " +
                "((f r t 10) 83.1 19) ((f r t 20) 80.6 12) ((f r t 30) 79 5) ((f r b 10) 91.8 31) " +
                "((f r b 20) 96.5 36) ((f r b 30) 103.5 40))"
        val deserialised = SeeMessage.deserialise(testMsg)
        val gson = GsonBuilder().setPrettyPrinting().create()
        println(gson.toJson(deserialised))
    }
}