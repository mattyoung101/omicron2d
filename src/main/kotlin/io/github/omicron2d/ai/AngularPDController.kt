/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

import io.github.omicron2d.utils.angleDistanceRad

/**
 * A special case of a P-D controller that works for angles. It knows about how angles wrap around, so doesn't produce
 * needlessly large correction values. **Note: works for radians only.**
 *
 * Duplicated from [PDController].
 */
class AngularPDController(var kp: Double, var kd: Double, var min: Double = Double.MIN_VALUE, var max: Double = Double.MAX_VALUE) {
    private var lastTime: Long = System.nanoTime()
    private var lastInput: Double = 0.0

    /**
     * Updates the controller.
     * @param input input to the control system, **in radians**
     * @param setPoint target value
     * @return correction to apply to system **in radians**
     */
    fun update(input: Double, setPoint: Double): Double {
        val currentTime = System.nanoTime()
        val elapsedTime = (currentTime - lastTime) / 1e+9

        val error = angleDistanceRad(setPoint, input)
        val derivative = (angleDistanceRad(input, lastInput)) / elapsedTime
        val correction = (kp * error) + (kd * derivative)

        lastTime = currentTime
        lastInput = input

        return correction.coerceIn(min, max)
    }
}