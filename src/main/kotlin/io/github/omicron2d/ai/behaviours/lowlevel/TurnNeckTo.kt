/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.lowlevel

import io.github.omicron2d.ai.behaviours.Behaviour
import io.github.omicron2d.utils.*
import org.tinylog.kotlin.Logger

/**
 * This behaviour turns the neck to a specified angle.
 * @param targetAngle target angle to turn head to, must be in -180 to 180 degrees
 */
class TurnNeckTo(val targetAngle: Double) : Behaviour() {
    private val tolerance = CURRENT_CONFIG.get().turnNeckToleranceDeg.toRadians()
    private val smoothing = CURRENT_CONFIG.get().turnNeckSmoothing
    private var lastObservedTick = -1

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        if (!ctx.world.getSelfPlayer().isKnown || ctx.world.getSelfPlayer().transform.theta == -1.0) {
            Logger.warn("Cannot calculate orientation, self information unknown!")
            return BehaviourStatus.FAILURE
        } else if (ctx.world.playMode == PlayMode.BEFORE_KICK_OFF) {
            // don't support any behaviours where the clock is not ticking up
            Logger.warn("TurnNeckTo not supported in current play mode: ${ctx.world.playMode}")
            return BehaviourStatus.FAILURE
        }

        if (ctx.world.getSelfPlayer().lastSeen == lastObservedTick) {
            // same vision tick, wait for next world model update
            return BehaviourStatus.RUNNING
        } else {
            // new vision data received, world model is updated, we can act now
            lastObservedTick = ctx.world.getSelfPlayer().lastSeen
        }

        // snap to the target angle, turn by the difference it between us now and the target
        // FIXME use neck angle reported from sense_body
        val currentAngle = ctx.world.getSelfPlayer().transform.theta
        ctx.neckResult = angleSignedDistance(currentAngle, targetAngle) * smoothing

        return if (angleUnsignedDistance(ctx.world.getSelfPlayer().transform.theta, targetAngle) <= tolerance){
            BehaviourStatus.SUCCESS
        } else {
            BehaviourStatus.RUNNING
        }
    }
}