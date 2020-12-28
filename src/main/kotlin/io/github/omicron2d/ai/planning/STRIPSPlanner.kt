/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.planning

import java.util.*

/**
 * Implements a simple STRIPS-like planning algorithm, see
 * [https://en.wikipedia.org/wiki/Stanford_Research_Institute_Problem_Solver].
 *
 * This code is ported from my GOAP implementation called goaplite, originally written in C:
 * [https://github.com/mattyoung101/goaplite/blob/master/goap.c]
 *
 * This is not an HTN planner, so it's quite simple and not so "smart". I believe it should suffice for the time being.
 */
class STRIPSPlanner : Planner {
    override fun calculatePlan(
        initialState: Map<String, Boolean>,
        targetState: Map<String, Boolean>,
        actions: List<PlanAction>
    ): Queue<PlanAction> {
        val queue: Queue<Node> = LinkedList()

        // add our initial world state to the queue
        val initial = Node(listOf(), initialState)
        queue.add(initial)

        return LinkedList()
    }

    /**
     * Compares two world states and returns true if they're functionally equivalent, ignoring extraneous keys
     */
    private fun compareWorldState(currentState: Map<String, Boolean>, goal: Map<String, Boolean>): Boolean {
        for (key in goal.keys){
            val curVal = currentState[key]
            val targetVal = goal[key]

            // interesting to note: STRIPS planners like GOAP make the closed world assumption, which we see on this line.
            // if curVal is NULL, it means that the key from the goal state was not found in the current state.
            // we assume this means it's false, but in fact it's just "unknown" - it could, in fact, be true.
            // see: https://en.wikipedia.org/wiki/Closed-world_assumption
            if (curVal == null || targetVal == null || curVal != targetVal){
                return false
            }
            // TODO check the above is correct, we may not need to check if targetVal is null
        }
        return true
    }

    /**
     * A node used in graph search.
     * @param parents actions who were executed previously (I THINK, need to check this)
     * @param worldState current world state at this node (sum of all parents world states, basically)
     */
    private data class Node(val parents: List<PlanAction>, val worldState: Map<String, Boolean>)
}