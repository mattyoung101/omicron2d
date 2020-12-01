/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import io.github.omicron2d.utils.AgentContext
import org.tinylog.kotlin.Logger

/**
 * Behaviour to rotate around in a circle, scanning the world for things.
 * @param isFastScan should we use wider vision, less accurate but we can complete a scan faster?
 * @param numScans number of scans or complete spins to do before exiting
 */
class WorldScanMovementBehaviour(private val isFastScan: Boolean, private val numScans: Int) : MovementBehaviour {
    private var startOrientation = -1.0

    override fun onEnter(ctx: AgentContext) {
        startOrientation = ctx.world.getSelfPlayer().transform.theta
        Logger.debug("World scan behaviour started at orientation $startOrientation rad")
    }

    override fun getDashVelocity(): Double {
        return 0.0 // don't move while scanning
    }

    override fun getTurnVelocity(): Double {
        // TODO calculate this
        return 1234.0
    }

    override fun isDone(ctx: AgentContext): Boolean {
        ctx.world.getSelfPlayer().transform.theta
        // TODO return true when we have finished the number of scans.
        return false
    }
}