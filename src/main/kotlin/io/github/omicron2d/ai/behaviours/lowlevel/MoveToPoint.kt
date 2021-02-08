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
import io.github.omicron2d.utils.BehaviourStatus
import io.github.omicron2d.utils.CURRENT_CONFIG
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import kotlin.math.atan2

/**
 * Behaviour which moves the agent to a given point using a PD controller.
 * TODO add support for moving to point while not facing forwards.
 * @param targetPoint point to go to
 * @param maxPower maximum amount of power that can be used in any one dash command (this is NOT total stamina used)
 * @param staminaSaver only sends a move command every second tick to save some stamina
 */
class MoveToPoint(val targetPoint: Vector2, val maxPower: Double, val staminaSaver: Boolean = false) : MovementBehaviour {
    private val xPD = PDController(CURRENT_CONFIG.get().moveKp, CURRENT_CONFIG.get().moveKd, -maxPower, maxPower)
    private val yPD = PDController(CURRENT_CONFIG.get().moveKp, CURRENT_CONFIG.get().moveKd, -maxPower, maxPower)
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh
    private var currentTicks = 0
    private var status = BehaviourStatus.RUNNING

    // TODO add better stamina planning instead of just max power

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        val myPos = ctx.world.getSelfPlayer().transform.pos
        return if (myPos.distance(targetPoint) <= threshold) BehaviourStatus.SUCCESS else status
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        // can't do much if we don't know anything about ourselves (words of wisdom right there!)
        if (!ctx.world.getSelfPlayer().isKnown){
            Logger.warn("Cannot calculate movement, self position unknown!")
            status = BehaviourStatus.FAILURE
            return Vector2(0.0, 0.0)
        }

        // stamina saver code
        // TODO this could be more advanced in future by analysing our velocity
        if (currentTicks++ % 2 == 0 && staminaSaver){
            return Vector2(0.0, 0.0)
        }

        // apply PD controller in cartesian first
        val myPos = ctx.world.getSelfPlayer().transform.pos
        val xCorrection = xPD.update(myPos.x, targetPoint.x)
        val yCorrection = yPD.update(myPos.y, targetPoint.y)

        // convert our relative cartesian correction vector into polar for the server
        val movementCart = Vector2(xCorrection, yCorrection)
        // for some reason we have to constrain this again to our max range
        val r = movementCart.magnitude().coerceIn(-maxPower, maxPower)
        val theta = atan2(movementCart.y, movementCart.x)

        // note that the final output is still in radians, -pi to pi as well in this case
        return Vector2(r, theta)
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        // use omnidirectional dash instead of turn
        return 0.0
    }

    override fun toString(): String {
        return "MoveToPoint(targetPoint=$targetPoint, maxPower=$maxPower)"
    }


}