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
import io.github.omicron2d.utils.angleUnsignedDistance
import org.tinylog.kotlin.Logger
import kotlin.math.abs

/**
 * Combines [MoveToPoint] and [TurnBodyTo] to look in the direction we are moving to.
 * Should be faster than moving along an angle.
 */
class MoveToPointLooking(val targetPoint: Vector2, val maxPower: Double, val staminaSaver: Boolean = false) : Behaviour() {
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh
    private val kp = CURRENT_CONFIG.get().moveLookingKp
    private val kd = CURRENT_CONFIG.get().moveLookingKd
    private val pd = PDController(kp, kd, -maxPower, maxPower)
    private var turnBodyTo: TurnBodyTo? = null
    private var reachedAngle = false

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        val myPos = ctx.world.getSelfPlayer().transform.pos
        val distance = myPos.dst(targetPoint)
        val correction = abs(pd.update(distance, 0.0))

        // first step: calculate move (if applicable, this will do nothing if we are still turning)
        if (reachedAngle) {
            ctx.moveResult.dash = Vector2(correction, 0.0)
            // always return from here so we don't calculate the angle
            return if (myPos.dst(targetPoint) <= threshold) BehaviourStatus.SUCCESS else BehaviourStatus.RUNNING
        }

        // assuming we have not run the move code yet, we must need to calculate the angle to turn to
        val currentAngle = ctx.world.getSelfPlayer().transform.theta
        val angleToTarget = targetPoint.cpy().sub(myPos).angleRad()

        // sometimes, the agent gets repositioned by the server and this breaks the angle
        // detect if the distance between our current angle and the target angle is off by more than the threshold
        // plus a small buffer, and if so, realign ourselves
        // TODO make this buffer a define
        if (reachedAngle && angleUnsignedDistance(currentAngle, angleToTarget) > threshold + 0.1){
            Logger.warn("Angle disruption detected! Realigning to $angleToTarget rad")
            reachedAngle = false
        }

        // if we don't currently have a turn body to behaviour, make a new one
        if (turnBodyTo == null){
            Logger.debug("Creating new TurnBodyTo, angle = $angleToTarget rad")
            turnBodyTo = TurnBodyTo(angleToTarget)
        }

        // update turn body to and check status
        when (turnBodyTo!!.onUpdate(ctx)) {
            BehaviourStatus.SUCCESS -> {
                Logger.debug("TurnBodyTo has completed, starting movement to $targetPoint")
                turnBodyTo = null
                reachedAngle = true
            }
            BehaviourStatus.FAILURE -> {
                Logger.warn("TurnBodyTo failed! Escalating failure")
                return BehaviourStatus.FAILURE
            }
            else -> {
                // still turning
                return BehaviourStatus.RUNNING
            }
        }

        // shouldn't happen
        return BehaviourStatus.RUNNING
    }

    override fun toString(): String {
        return "MoveToPointLooking(targetPoint=$targetPoint, maxPower=$maxPower, staminaSaver=$staminaSaver)"
    }
}