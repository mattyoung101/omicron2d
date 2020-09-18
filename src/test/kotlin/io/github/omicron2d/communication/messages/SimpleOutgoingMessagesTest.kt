/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package io.github.omicron2d.communication.messages

import org.junit.Assert.*
import org.junit.Test

class OutgoingInitMessageTest {
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