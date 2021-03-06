/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.lowlevel

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.ai.PDController
import io.github.omicron2d.ai.behaviours.Behaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import io.github.omicron2d.utils.CURRENT_CONFIG
import org.tinylog.kotlin.Logger
import kotlin.math.atan2

/**
 * Behaviour which moves the agent to a given point using a PD controller.
 * TODO add support for moving to point while not facing forwards.
 * @param targetPoint point to go to
 * @param maxPower maximum amount of power that can be used in any one dash command (this is NOT total stamina used)
 * @param staminaSaver only sends a move command every second tick to save some stamina
 */
class MoveToPoint(val targetPoint: Vector2, val maxPower: Double, val staminaSaver: Boolean = false) : Behaviour() {
    private val xPD = PDController(CURRENT_CONFIG.get().moveKp, CURRENT_CONFIG.get().moveKd, -maxPower, maxPower)
    private val yPD = PDController(CURRENT_CONFIG.get().moveKp, CURRENT_CONFIG.get().moveKd, -maxPower, maxPower)
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh
    private var currentTicks = 0
    private var status = BehaviourStatus.RUNNING

    // TODO add better stamina planning instead of just max power

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        // can't do much if we don't know anything about ourselves (words of wisdom right there!)
        if (!ctx.world.getSelfPlayer().isKnown){
            Logger.warn("Cannot calculate movement, self position unknown!")
            ctx.moveResult.dash = Vector2(0.0, 0.0)
            return BehaviourStatus.FAILURE
        }

        // stamina saver code
        // TODO this could be more advanced in future by analysing our velocity
        if (currentTicks++ % 2 == 0 && staminaSaver){
            ctx.moveResult.dash = Vector2(0.0, 0.0)
            return BehaviourStatus.RUNNING
        }

        // apply PD controller in cartesian first
        val myPos = ctx.world.getSelfPlayer().transform.pos
        val xCorrection = xPD.update(myPos.x, targetPoint.x)
        val yCorrection = yPD.update(myPos.y, targetPoint.y)

        // convert our relative cartesian correction vector into polar for the server
        val movementCart = Vector2(xCorrection, yCorrection)
        // for some reason we have to constrain this again to our max range
        val r = movementCart.len().coerceIn(-maxPower, maxPower)
        val theta = atan2(movementCart.y, movementCart.x)

        // note that the final output is still in radians, -pi to pi as well in this case
        ctx.moveResult.dash = Vector2(r, theta)

        return if (myPos.dst(targetPoint) <= threshold) BehaviourStatus.SUCCESS else status
    }

    override fun toString(): String {
        return "MoveToPoint(targetPoint=$targetPoint, maxPower=$maxPower, staminaSaver=$staminaSaver)"
    }
}