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
 * Interface for various planning algorithms.
 *
 * Nomenclature for this is from: https://en.wikipedia.org/wiki/Stanford_Research_Institute_Problem_Solver
 */
interface Planner {
    /**
     * Calculates the optimal plan to traverse from the world state in initialState to the world state in targetState.
     * @param initialState current world state containing list of named conditions (i.e., propositional variables)
     * @param targetState goal state, where we want to end up, list of named conditions (i.e., propositional variables)
     * @param actions list of all actions available to the agent
     * @return the plan, an ordered queue of actions the agent must complete
     */
    fun calculatePlan(
        initialState: Map<String, Boolean>,
        targetState: Map<String, Boolean>,
        actions: List<PlanAction>
    ): Queue<PlanAction>
}