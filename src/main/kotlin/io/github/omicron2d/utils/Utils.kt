/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import mikera.vectorz.Vector2
import org.apache.commons.math3.linear.*
import kotlin.math.PI
import kotlin.math.abs

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

/** Distance between angles in radians. Source: [https://stackoverflow.com/a/7571008/5007892] */
fun angleDistanceRad(a: Double, b: Double): Double {
    val phi = abs(b - a) % PI2
    return if (phi > PI) PI2 - phi else phi
}

/**
 * Represents a 2D position and rotation.
 * @param pos position of the agent, measured with (0,0) being centre of field
 * @param theta angle in radians, counter-clockwise (standard trig format)
 */
data class Transform2D(val pos: Vector2 = Vector2(0.0, 0.0), val theta: Double = 0.0)