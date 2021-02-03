/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.lowlevel

import io.github.omicron2d.ai.AngularPDController
import io.github.omicron2d.ai.PDController
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.CURRENT_CONFIG
import io.github.omicron2d.utils.PI2
import mikera.vectorz.Vector2

/**
 * Behaviour which moves the agent to a given point using a PD controller.
 * @param targetPoint point to go to
 * @param maxPower maximum amount of power that can be used in any one dash command (this is NOT total stamina used)
 */
class MoveToPoint(private val targetPoint: Vector2, private val maxPower: Double) : MovementBehaviour {
    private val speedPD = PDController(CURRENT_CONFIG.get().moveSpeedKp, CURRENT_CONFIG.get().moveSpeedKd, -maxPower, maxPower)
    private val anglePD = AngularPDController(CURRENT_CONFIG.get().moveAngleKp, CURRENT_CONFIG.get().moveAngleKd, -PI2, PI2)
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh

    override fun onEnter(ctx: AgentContext) {
        // in future we would do better stamina planning here
    }

    override fun isDone(ctx: AgentContext): Boolean {
        val myPos = ctx.world.getSelfPlayer().transform.pos
        return myPos.distance(targetPoint) <= threshold
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        // first check if we know our position, if not, bail
        if (!ctx.world.getSelfPlayer().isKnown){
            println("MoveToPoint position unknown")
            return Vector2(0.0, 0.0)
        }

        val myPos = ctx.world.getSelfPlayer().transform.pos
        val currentAngle = ctx.world.getSelfPlayer().transform.theta // radians
        val currentDist = myPos.distance(targetPoint)
        val angleToTarget = myPos.angle(targetPoint) // radians

        val angleCorrection = anglePD.update(currentAngle, angleToTarget) // radians
        // stop just inside threshold circle
        // FIXME why is this negative????? it doesn't work :/
        val speedCorrection = speedPD.update(currentDist, threshold - 1.0)

        // final angle is indeed still in radians, it is converted just before being sent to the server
        return Vector2(speedCorrection, angleCorrection)
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        // no contribution to turn angle
        return 0.0
    }
}