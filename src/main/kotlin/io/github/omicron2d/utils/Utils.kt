/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.ai.behaviours.Behaviour
import io.github.omicron2d.ai.world.HighLevelWorldModel
import io.github.omicron2d.ai.world.ICPLocalisation
import org.apache.commons.math3.linear.*
import kotlin.math.*

fun makeCommonsVector(x: Double, y: Double): RealVector {
    return ArrayRealVector(doubleArrayOf(x, y))
}

fun makeCommonsVector(vec: Vector2): RealVector {
    return ArrayRealVector(doubleArrayOf(vec.x, vec.y))
}

/** Creates a matrix of all ones */
fun createOnesMatrix(rows: Int, cols: Int): RealMatrix {
    return createFilledMatrix(rows, cols, 1.0)
}

/**
 * @param set value to fill matrix with
 */
fun createFilledMatrix(rows: Int, cols: Int, set: Double): RealMatrix {
    val mat = MatrixUtils.createRealMatrix(rows, cols)
    mat.walkInOptimizedOrder(object : DefaultRealMatrixChangingVisitor() {
        override fun visit(row: Int, column: Int, value: Double): Double {
            return set
        }
    })
    return mat
}

/** Same as numpy.mean(matrix, axis=0). Returned matrix will have `matrix.columnDimension` cols, and 1 row. */
fun matrixMeanCols(matrix: RealMatrix): RealMatrix {
    // note on numpy dimension ordering: https://stackoverflow.com/a/52468964/5007892
    val outMat = MatrixUtils.createRealMatrix(1, matrix.columnDimension)
    for (i in 0 until outMat.columnDimension){
        val mean = matrix.getColumn(i).sum() / matrix.rowDimension.toDouble()
        outMat.setEntry(0, i, mean)
    }
    return outMat
}

/** Unsigned distance between angles in radians. Source: [https://stackoverflow.com/a/7571008/5007892] */
fun angleUnsignedDistance(a: Double, b: Double): Double {
    val phi = abs(b - a) % PI2
    return if (phi > PI) PI2 - phi else phi
}

/**
 * Signed distance between two angles in radians. Source: [https://stackoverflow.com/a/2007279/5007892]
 * @param y current angle
 * @param x target angle
 */
fun angleSignedDistance(y: Double, x: Double): Double {
    return atan2(sin(x - y), cos(x - y))
}

/**
 * Converts this angle in radians to degrees, does not do any range conversions.
 */
fun Double.toDegrees(): Double {
    return this * RAD_DEG
}

/**
 * Converts this angle in degrees to radians, does not do any range conversions.
 */
fun Double.toRadians(): Double {
    return this * DEG_RAD
}

/**
 * Wrapper around [ICPLocalisation.correctPolarObservation] for easier use in agents
 * @param direction angle to the object, -180 to 180 (this is raw server format, NOT processed 0-360)
 * @param distance distance to the object
 * @param transform localised agent transform
 * @return absolute cartesian coordinates of this object
 */
fun calculateAbsolutePosition(direction: Int, distance: Double, transform: Transform2D): Vector2 {
    // localiser needs 0-360
    val fixedDirection = (direction.toDouble() % 360.0) % 360.0
    val observation = ObjectObservationPolar(distance, fixedDirection)
    return ICPLocalisation.correctPolarObservation(observation, transform.pos)
}

/**
 * Represents a 2D position and rotation.
 * @param pos position of the agent, measured with (0,0) being centre of field
 * @param theta angle **in radians**, counter-clockwise (standard trig format). If exactly -1.0, it is not known.
 */
data class Transform2D(val pos: Vector2 = Vector2(0.0, 0.0), val theta: Double = 0.0)
// TODO move the above to SoccerAgents

/**
 * Data class that holds the context that something, usually a [Behaviour], is executing in.
 */
data class AgentContext(val world: HighLevelWorldModel, val time: Int){
    // TODO only for the debugger!!
    override fun toString(): String {
        return ""
    }
}