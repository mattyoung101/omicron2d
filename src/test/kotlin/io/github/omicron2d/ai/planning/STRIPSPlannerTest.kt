/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.planning

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class STRIPSPlannerTest {
    @Test
    fun simplePlannerTest(){
        val planText = File("src/test/resources/actions_test.omplan").readText()
        val actions = PlanConfigParser.parseActions(planText)

        val planner = STRIPSPlanner()
        val initialState = mutableMapOf<String, Boolean>()
        initialState["IsEpic"] = true
        initialState["IsBad"] = false
        val targetState = mapOf(Pair("A3Done", true))

        val plan = planner.calculatePlan(initialState, targetState, actions)
        println("Plan calculated:")
        println(plan.map { it.name })

        assertTrue(plan.isNotEmpty())
    }

    @Test
    fun failureTest(){
        val planText = File("src/test/resources/actions_test.omplan").readText()
        val actions = PlanConfigParser.parseActions(planText)

        val planner = STRIPSPlanner()
        val initialState = mutableMapOf<String, Boolean>()
        initialState["IsEpic"] = true
        initialState["IsBad"] = false
        val targetState = mapOf(Pair("A3Done", true), Pair("CauseFailure", true))

        val plan = planner.calculatePlan(initialState, targetState, actions)
        assertTrue(plan.isEmpty())
    }

    @Test
    fun complexPlannerTest(){
        val planText = File("src/test/resources/actions_buildhouse.omplan").readText()
        val actions = PlanConfigParser.parseActions(planText)

        val planner = STRIPSPlanner()
        val initialState = mutableMapOf<String, Boolean>()
        val targetState = mapOf(Pair("BuiltHouse", true))

        val plan = planner.calculatePlan(initialState, targetState, actions)
        println("Plan calculated!")
        println(plan.map { it.name })

        assertTrue(plan.isNotEmpty())
    }
}