/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.lowlevel

import io.github.omicron2d.ai.PDController
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.CURRENT_CONFIG
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import kotlin.math.atan2

/**
 * Behaviour which moves the agent to a given point using a PD controller.
 * @param targetPoint point to go to
 * @param maxPower maximum amount of power that can be used in any one dash command (this is NOT total stamina used)
 */
class MoveToPoint(private val targetPoint: Vector2, private val maxPower: Double) : MovementBehaviour {
    private val xPD = PDController(CURRENT_CONFIG.get().moveKp, CURRENT_CONFIG.get().moveKd, -maxPower, maxPower)
    private val yPD = PDController(CURRENT_CONFIG.get().moveKp, CURRENT_CONFIG.get().moveKd, -maxPower, maxPower)
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh

    override fun onEnter(ctx: AgentContext) {
        // in future we would do better stamina planning here
    }

    override fun isDone(ctx: AgentContext): Boolean {
        val myPos = ctx.world.getSelfPlayer().transform.pos
        return myPos.distance(targetPoint) <= threshold
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        // can't do much if we don't know anything about ourselves (words of wisdom right there!)
        if (!ctx.world.getSelfPlayer().isKnown){
            Logger.warn("Self position unknown!")
            // FIXME come up with a better way to handle this
            // (either ask our friends for help, or move back to a previous pos?)
            return Vector2(0.0, 0.0)
        }

        // apply PD controller in cartesian first
        val myPos = ctx.world.getSelfPlayer().transform.pos
        val xCorrection = xPD.update(myPos.x, targetPoint.x)
        val yCorrection = yPD.update(myPos.y, targetPoint.y)

        // convert our relative cartesian correction vector into polar for the server
        val movementCart = Vector2(xCorrection, yCorrection)
        val r = movementCart.magnitude()
        val theta = atan2(movementCart.y, movementCart.x)

        // note that the final output is still in radians
        return Vector2(r, theta)
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        // use omnidirectional dash instead of turn
        return 0.0
    }
}