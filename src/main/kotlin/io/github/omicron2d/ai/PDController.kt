/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

/**
 * Implements a simple PD controller. Correction will be constrained between `min` and `max`, which are by default
 * `Double.MIN_VALUE` and `Double.MAX_VALUE` respectively. Integral is not used.
 *
 * Sources used:
 * - [https://github.com/mattyoung101/omicron/blob/master/esp32/main/pid.c]
 * - [https://en.wikipedia.org/wiki/PID_controller#Pseudocode]
 */
class PDController(var kp: Double, var kd: Double, var min: Double = Double.MIN_VALUE, var max: Double = Double.MAX_VALUE) {
    private var lastTime: Long = System.nanoTime()
    private var lastInput: Double = 0.0

    /**
     * Updates the controller.
     * @param input input to the control system
     * @param setPoint target value
     * @return correction to apply to system
     */
    fun update(input: Double, setPoint: Double): Double {
        val currentTime = System.nanoTime()
        val elapsedTime = (currentTime - lastTime) / 1e+9
        lastTime = currentTime
        lastInput = input
        
        val error = setPoint - input
        val derivative = (input - lastInput) / elapsedTime
        val correction = (kp * error) + (kd * derivative)
        return correction.coerceIn(min, max)
    }
}