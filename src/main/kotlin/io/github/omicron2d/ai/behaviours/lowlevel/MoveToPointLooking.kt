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
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import io.github.omicron2d.utils.CURRENT_CONFIG
import org.tinylog.kotlin.Logger

/**
 * Combines [MoveToPoint] and [TurnBodyTo] to look in the direction we are moving to.
 */
class MoveToPointLooking(val targetPoint: Vector2, val maxPower: Double, val staminaSaver: Boolean = false) : MovementBehaviour {
    private val threshold = CURRENT_CONFIG.get().movePointReachedThresh
    private var status = BehaviourStatus.RUNNING
    private var turnBodyTo: TurnBodyTo? = null
    private var reachedAngle = false

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        val myPos = ctx.world.getSelfPlayer().transform.pos
        return if (myPos.dst(targetPoint) <= threshold) BehaviourStatus.SUCCESS else status
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        return if (reachedAngle) Vector2(maxPower, 0.0) else Vector2(0.0, 0.0)
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        if (reachedAngle) return 0.0

        val myPos = ctx.world.getSelfPlayer().transform.pos
        val angleToTarget = myPos.angleRad(targetPoint)

        // if we don't currently have a turn body to behaviour, make a new one
        if (turnBodyTo == null){
            Logger.debug("Creating new TurnBodyTo, angle = $angleToTarget rad")
            turnBodyTo = TurnBodyTo(angleToTarget)
        }

        // update the turn
        val angle = turnBodyTo!!.calculateTurn(ctx)

        // check if done
        val turnStatus = turnBodyTo!!.reportStatus(ctx)
        if (turnStatus != BehaviourStatus.RUNNING){
            Logger.debug("TurnBodyTo has completed, starting movement")
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
}