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
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.CURRENT_CONFIG
import io.github.omicron2d.utils.angleDistanceRad
import io.github.omicron2d.utils.toRadians
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import kotlin.math.PI

/**
 * This behaviour turns the body to a specified angle (uses a P-D controller)
 * @param targetAngle body angle to be reached **in radians**
 */
class TurnBodyTo(val targetAngle: Double) : MovementBehaviour {
    // in the config file, kp and kd are in degrees - but we work in radians in the code, so convert them
    private val kp = CURRENT_CONFIG.get().turnBodyKp.toRadians()
    private val kd = CURRENT_CONFIG.get().turnBodyKd.toRadians()

    private val controller = AngularPDController(kp, kd, -PI, PI)
    private val tolerance = CURRENT_CONFIG.get().turnBodyToleranceDeg.toRadians()

    override fun isDone(ctx: AgentContext): Boolean {
        return angleDistanceRad(ctx.world.getSelfPlayer().transform.theta, targetAngle) <= tolerance
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        if (!ctx.world.getSelfPlayer().isKnown || ctx.world.getSelfPlayer().transform.theta == -1.0) {
            Logger.warn("Cannot calculate orientation, self information unknown!")
            return 0.0
        }

        val currentAngle = ctx.world.getSelfPlayer().transform.theta
        val correction = controller.update(currentAngle, targetAngle)
        return correction

//        println("Current angle: ${ctx.world.getSelfPlayer().transform.theta.toDegrees()} deg")
//        return 1.0 * DEG_RAD
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        return Vector2(0.0, 0.0)
    }

    override fun toString(): String {
        return "TurnBodyTo(targetAngle=$targetAngle rad)"
    }
}