/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.lowlevel

import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import mikera.vectorz.Vector2

/**
 * This behaviour makes the agent sit at a point for a certain number of milliseconds.
 * @param millis number of milliseconds to wait for
 */
class Sit(private val millis: Int) : MovementBehaviour {
    private var startTime = 0L

    override fun onEnter(ctx: AgentContext) {
        startTime = System.currentTimeMillis()
    }

    override fun isDone(ctx: AgentContext): Boolean {
        return System.currentTimeMillis() - startTime > millis
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        return Vector2(0.0, 0.0)
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        return 0.0
    }

    override fun toString(): String {
        return "Sit(millis=$millis)"
    }


}