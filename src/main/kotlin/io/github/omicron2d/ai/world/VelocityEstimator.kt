/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import com.badlogic.gdx.math.Vector2
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.tinylog.kotlin.Logger

/**
 * Used to estimate velocity of field objects when distChange/dirChange is not available.
 *
 * Ported from my Python predictor code for Team Omicron RoboCup Jr Soccer Simulation February 2021.
 */
class VelocityEstimator(private val ringBufferSize: Int = 16) {
    private val stats = DescriptiveStatistics(ringBufferSize)
    private var lastPos = Vector2(0.0, 0.0)
    private var lastDeltaPos = Vector2(0.0, 0.0)
    private var lastTime = 0.0
    private var lastVelocity = 0.0
    private var hasSetInitialPos = false

    fun setInitialPos(pos: Vector2){
        if (!hasSetInitialPos){
            Logger.debug("Setting initial position to $pos")
            lastPos = pos
            hasSetInitialPos = true
        }
    }

    /**
     * Calculates a new velocity for the object.
     * @param currentPos current position of the object
     * @param currentTime current Omicron measured ticks since launch
     * @return velocity of object
     */
    fun pushMeasurement(currentPos: Vector2, currentTime: Int): Vector2 {

        return Vector2(0.0, 0.0) // FIXME only for testing
    }

    companion object {
        /**
         * Predicts the position of the object forward in time.
         * @param currentPos current position of object
         * @param velocity current velocity of object, as determined by [VelocityEstimator.submit]
         * @param futureTime number of ticks to predict forward in future
         * @return position of the object, moving at the given velocity, futureTime ticks in the future
         */
        fun predict(currentPos: Vector2, velocity: Vector2, futureTime: Int): Vector2 {
            val v = velocity.cpy()
            v.scl(futureTime.toDouble())
            v.add(currentPos)
            return v
        }
    }

}