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
import io.github.omicron2d.utils.*
import org.tinylog.kotlin.Logger

/**
 * This behaviour turns the body to a specified angle (snaps directly to it using angle difference).
 * @param targetAngle body angle to be reached **in radians**
 */
class TurnBodyTo(val targetAngle: Double) : MovementBehaviour {
    private val tolerance = CURRENT_CONFIG.get().turnBodyToleranceDeg.toRadians()
    private val smoothing = CURRENT_CONFIG.get().turnBodySmoothing
    private var lastObservedTick = -1
    private var status = BehaviourStatus.RUNNING

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        return if (angleUnsignedDistance(ctx.world.getSelfPlayer().transform.theta, targetAngle) <= tolerance){
            BehaviourStatus.SUCCESS
        } else {
            status
        }
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        if (!ctx.world.getSelfPlayer().isKnown || ctx.world.getSelfPlayer().transform.theta == -1.0) {
            Logger.warn("Cannot calculate orientation, self information unknown!")
            status = BehaviourStatus.FAILURE
            return 0.0
        } else if (ctx.world.playMode == PlayMode.BEFORE_KICK_OFF) {
            // don't support any behaviours where the clock is not ticking up
            Logger.warn("TurnBodyTo not supported in current play mode: ${ctx.world.playMode}")
            status = BehaviourStatus.FAILURE
            return 0.0
        }

        // our problem originally was that, because server vision runs at 250ms but thinking runs ever 100ms,
        // the TurnBodyTo behaviour doesn't actually know that it has turned yet (since new vision and localisation data
        // hasn't come in yet), and keeps spinning around.
        // what we do here is make sure we only update the behaviour when the tick changed, otherwise skip processing.
        if (ctx.world.getSelfPlayer().lastSeen == lastObservedTick) {
            // same vision tick, wait for next world model update
            return 0.0
        } else {
            // new vision data received, world model is updated, we can act now
            lastObservedTick = ctx.world.getSelfPlayer().lastSeen
        }

        // snap to the target angle, turn by the difference it between us now and the target
        val currentAngle = ctx.world.getSelfPlayer().transform.theta
        return angleSignedDistance(currentAngle, targetAngle) * smoothing
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        return Vector2(0.0, 0.0)
    }

    override fun toString(): String {
        return "TurnBodyTo(targetAngle=$targetAngle rad)"
    }
}