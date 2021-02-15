/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

@file:Suppress("MoveVariableDeclarationIntoWhen")

package io.github.omicron2d.utils

import com.badlogic.gdx.math.Vector2
import kotlin.math.roundToInt

/**
 * Utility class for counting which quadrants of a circle have the highest score.
 * Used to calculate the busiest directions for the PlayerAgent.
 *
 * Quadrants reference: [https://x-engineer.org/wp-content/uploads/2016/10/Four-quadrants-circle.jpg]
 * (in case link goes down: just the ones on the unit circle).
 */
class QuadrantCounter {
    /** key = quadrant number, value = quadrant score */
    val quadrants = sortedMapOf<Int, Int>()

    init {
        for (i in 1 until 5){
            quadrants[i] = 0
        }
    }

    /**
     * Increment score of quadrant.
     * @param angle angle in **radians**
     * @param score score to increment quadrant by
     */
    fun assignScore(angle: Double, score: Int){
        val degrees = ((angle.toDegrees() + 360.0) % 360.0).roundToInt()
        when (degrees) {
            in 0..90 -> {
                quadrants[1] = quadrants[1]!! + score
            }
            in 90..180 -> {
                quadrants[2] = quadrants[2]!! + score
            }
            in 180..270 -> {
                quadrants[3] = quadrants[3]!! + score
            }
            in 270..359 -> {
                quadrants[4] = quadrants[4]!! + score
            }
        }
    }

    fun assignScore(selfPos: Vector2, targetPos: Vector2, score: Int){
        assignScore(selfPos.angleRad(targetPos), score)
    }

    /**
     * Orders the quadrants by the ones with the most activity, then returns the angle for each quadrants centre
     * (can be fed directly into TurnBodyTo).
     */
    fun calculateTurnAngles(): Array<Int> {
        val sorted = quadrants.toSortedMap(compareBy { it })
        return arrayOf()
    }

    companion object {
        // scores for each type of object
        const val FRIENDLY_PLAYER = 1
        const val ENEMY_PLAYER = 2
        const val BALL = 3
    }
}