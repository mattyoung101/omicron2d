/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.highlevel

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import org.tinylog.kotlin.Logger

/**
 * Scans the world around the agent when it is stationary, using `turn` commands.
 * It also calculates the busiest sections of the world to look at first.
 * @param numScans number of scans or complete spins to do before exiting
 */
class StationaryScan(private val numScans: Int = 1) : MovementBehaviour {
    private var status = BehaviourStatus.RUNNING

    init {
        if (numScans <= 0){
            Logger.error("Number of scans must be >= 0")
            status = BehaviourStatus.FAILURE
        }
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        // TODO calculate this
        return 1234.0
    }

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        return status
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        return Vector2(0.0, 0.0)
    }
}