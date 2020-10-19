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

/**
 * Creates an ArrayRealVector with the given x and y using Apache Commons Math
 */
fun makeCommonsVector(x: Double, y: Double): RealVector {
    return ArrayRealVector(doubleArrayOf(x, y))
}

fun makeCommonsVector(vec: Vector2): RealVector {
    return ArrayRealVector(doubleArrayOf(vec.x, vec.y))
}

fun createOnesMatrix(rows: Int, cols: Int): RealMatrix {
    return createFilledMatrix(rows, cols, 1.0)
}

fun createFilledMatrix(rows: Int, cols: Int, value: Double): RealMatrix {
    val mat = MatrixUtils.createRealMatrix(rows, cols)
    mat.walkInOptimizedOrder(object : DefaultRealMatrixChangingVisitor() {
        override fun visit(row: Int, column: Int, value: Double): Double {
            return value
        }
    })
    return mat
}

/** Same as numpy.mean(matrix, axis=0). Returned matrix will have `matrix.columnDimension` cols, and 1 row. */
// note on numpy dimension ordering: https://stackoverflow.com/a/52468964/5007892
fun matrixMeanCols(matrix: RealMatrix): RealMatrix {
    val outMat = MatrixUtils.createRealMatrix(1, matrix.columnDimension)
    for (i in 0 until outMat.columnDimension){
        val mean = matrix.getColumn(i).sum() / matrix.rowDimension.toDouble()
        outMat.setEntry(0, i, mean)
    }
    return outMat
}

/**
 * Represents a 2D position and rotation.
 * @param pos position of the agent, measured with (0,0) being centre of field
 * @param theta angle in radians, counter-clockwise (standard trig format)
 */
data class Transform2D(val pos: Vector2, val theta: Double)