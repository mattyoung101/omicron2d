/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.planning

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class PlanConfigParserTest {
    @Test
    fun testParsePlan(){
        val planText = File("src/test/resources/actions_test.omplan").readText()
        val plan = PlanConfigParser.parseActions(planText)
        println("Plan: $plan")

        // we're mainly interested in it not crashing so our checks aren't too rigorous
        assertEquals(3, plan.size)
        assertEquals("Action3", plan[2].name)
    }
}