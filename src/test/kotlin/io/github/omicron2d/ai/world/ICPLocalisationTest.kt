/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import io.github.omicron2d.utils.matrixMeanCols
import org.apache.commons.math3.linear.MatrixUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class ICPLocalisationTest {
    @Test
    fun testMatMeanCols(){
        val col1 = doubleArrayOf(1.0, 2.0)
        val col2 = doubleArrayOf(3.0, 4.0)
        val col3 = doubleArrayOf(5.0, 6.0)
        val matrixArray = arrayOf(col1, col2, col3)
        val matrix = MatrixUtils.createRealMatrix(matrixArray)

        val expectedArray = arrayOf(doubleArrayOf(3.0, 4.0))
        val expected = MatrixUtils.createRealMatrix(expectedArray)

        val avg = matrixMeanCols(matrix)
        assertEquals(expected, avg)
    }
}