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
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import io.github.omicron2d.utils.CURRENT_CONFIG
import io.github.omicron2d.utils.angleUnsignedDistance
import org.tinylog.kotlin.Logger
import kotlin.math.PI
import kotlin.math.abs

/**
 * Combines [MoveToPoint] and [TurnBodyTo] to look in the direction we are moving to.
 * Should be faster than moving along an angle.
 */
class MoveToPointLooking(val targetPoint: Vector2, val maxPower: Double, val staminaSaver: Boolean = false) : MovementBehaviour {
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh
    private val kp = CURRENT_CONFIG.get().moveLookingKp
    private val kd = CURRENT_CONFIG.get().moveLookingKd
    private val pd = PDController(kp, kd, -maxPower, maxPower)
    private var status = BehaviourStatus.RUNNING
    private var turnBodyTo: TurnBodyTo? = null
    private var reachedAngle = false

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        val myPos = ctx.world.getSelfPlayer().transform.pos
        return if (myPos.dst(targetPoint) <= threshold) BehaviourStatus.SUCCESS else status
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        val distance = ctx.world.getSelfPlayer().transform.pos.dst(targetPoint)
        val correction = abs(pd.update(distance, 0.0))
        return if (reachedAngle) Vector2(correction, 0.0) else Vector2(0.0, 0.0)
    }

    // TODO if it keeps bugging, try using a PD controller on the angle instead (may be too inaccurate)

    override fun calculateTurn(ctx: AgentContext): Double {
        val currentAngle = ctx.world.getSelfPlayer().transform.theta
        val myPos = ctx.world.getSelfPlayer().transform.pos
        val angleToTarget = myPos.cpy().sub(targetPoint).angleRad() - PI

        // sometimes, the agent gets repositioned by the server and this breaks the angle
        // detect if the distance between our current angle and the target angle is off by more than the threshold
        // plus a small buffer, and if so, realign ourselves
        if (reachedAngle && angleUnsignedDistance(currentAngle, angleToTarget) > threshold + 0.1){
            Logger.warn("Angle disruption detected! Realigning to $angleToTarget rad")
            reachedAngle = false
        } else if (reachedAngle){
            return 0.0
        }

        // if we don't currently have a turn body to behaviour, make a new one
        if (turnBodyTo == null){
            Logger.debug("Creating new TurnBodyTo, angle = $angleToTarget rad")
            turnBodyTo = TurnBodyTo(angleToTarget)
        }

        val angle = turnBodyTo!!.calculateTurn(ctx)

        // check if done and report errors if necessary
        val turnStatus = turnBodyTo!!.reportStatus(ctx)
        if (turnStatus != BehaviourStatus.RUNNING){
            Logger.debug("TurnBodyTo has completed, starting movement to $targetPoint")
            turnBodyTo = null
            reachedAngle = true

            if (turnStatus == BehaviourStatus.FAILURE){
                Logger.warn("TurnBodyTo failed! Escalating failure")
                status = BehaviourStatus.FAILURE
                return 0.0
            }
        }

        return angle
    }

    override fun toString(): String {
        return "MoveToPointLooking(targetPoint=$targetPoint, maxPower=$maxPower, staminaSaver=$staminaSaver)"
    }
}