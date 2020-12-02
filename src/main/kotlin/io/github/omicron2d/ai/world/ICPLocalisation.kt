/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import io.github.omicron2d.utils.*
import mikera.vectorz.Vector2
import org.apache.commons.math3.linear.*
import org.jfree.chart.ChartFactory
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.tinylog.kotlin.Logger
import java.util.*
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/**
 * This class implements localisation by using an element of the iterative closest point (ICP) algorithm, the best
 * fit transform. Typically, we use ICP when we want to align two point sets without any known correspondences. In this
 * case, a K-D tree is used to find nearest neighbour correspondences for each point, and the best fit transform is
 * run iteratively. However, because in soccer2d each marker has an ID, we actually have known correspondences (we know
 * say that observed marker `f r t` corresponds to the known position of the marker `f r t`), and thus
 * can skip the nearest neighbour search entirely. This means we can also skip the iteration, and use the best fit
 * transform directly. Thus, we calculate the (x,y,theta) transform of our robot.
 *
 * To elaborate more, when we see flag `f r t` at an observed angle and distance, we can convert this from relative polar
 * to relative Cartesian. After that, we can look up its known absolute position [MarkerManager.markers], and doing this
 * repeatedly will calculate our position transform. The orientation is calculated using a SVD.
 *
 * This implementation of ICP is a port of [https://github.com/ClayFlannigan/icp], originally written in Python and under
 * the Apache 2.0 licence.
 */
object ICPLocalisation {
    /** matrix formatter for printing python/numpy matrices */
    private val formatter = RealMatrixFormat("[", "]", "[", "]", ", \n", ", ")

    /**
     * Performs the localisation by calculating the best fit transform.
     * This code is a port of the function best_fit_transform in icp.py. See [ICPLocalisation] for more details.
     * @param observations a hash map of flag names, and their respective angles (degrees, 0-360) and distances relative to the player
     * @return the estimated transform (position and rotation) of the agent
     */
    fun performLocalisation(observations: Map<String, ObjectObservationPolar>): Transform2D {
        // convert polar observations to a list of cartesian observations
        val observedPointsMap = makeCartesian(observations)

        // first, we find correlating points to our observed points. that is, for every observed point, we find its matching
        // absolute position from the marker manager
        val correlatedPointsMap = MarkerManager.markers.filter { it.key in observedPointsMap.keys }

        if (observedPointsMap.size != correlatedPointsMap.size){
            Logger.warn("Localisation points size mismatch!")
            Logger.warn("Observed points: ${observedPointsMap.keys}")
            Logger.warn("Correlated points: ${correlatedPointsMap.keys}")
        }

        // convert both to matrices
        val observedPoints = makeMatrix(observedPointsMap.values) // "A"
        val correlatedPoints = makeMatrix(correlatedPointsMap.values) // "B"

        // get number of dimensions (omicron2d note: should always be 2)
        val m = observedPoints.columnDimension

        // translate points to their centroids
        val centroidA = matrixMeanCols(observedPoints)
        val centroidB = matrixMeanCols(correlatedPoints)
        // note our imitation of numpy broadcasting behaviour here
        // we need to duplicate each row from centroidA to match with the rows in the A matrix (observedPoints)
        // to confirm this, in numpy execute: np.broadcast_to(centroid_A, A.shape)
        val aa = observedPoints.subtract(matrixBroadcast(centroidA, observedPoints.rowDimension))
        val bb = correlatedPoints.subtract(matrixBroadcast(centroidB, correlatedPoints.rowDimension))

        // rotation matrix
        val h = aa.transpose().multiply(bb)
        val svd = SingularValueDecomposition(h)
        val u = svd.u
        val vt = svd.vt
        var r = vt.transpose().multiply(u.transpose())

        // special reflection case
        if (LUDecomposition(r).determinant < 0){
            // omicron2d note: this appears to be correct, but I'm not sure, we need to check on this if it acts up
            // python code was: Vt[m - 1, :] *= -1
            for (col in 0 until m - 1){
                for (row in 0 until r.rowDimension - 1){
                    vt.multiplyEntry(row, col, -1.0)
                }
            }
            r = vt.transpose().multiply(u.transpose())
        }

        // translation
        val t = centroidB.transpose().subtract(r.multiply(centroidA.transpose()))
        // omicron2d note: don't bother calculating homogenous transform matrix, we don't use it

        // all omicron2d code follows:
        // we've got the whole rotation matrix, but we just want the angle. seems as though there's many ways of doing this
        // source for this implementation: https://math.stackexchange.com/a/301335
        // [cos, -sin],
        // [sin, cos]
        val plusCosTerm = r.getEntry(0, 0) // +cos(theta) term in 2D rotation matrix
        val plusSinTerm = r.getEntry(1, 0) // +sin(theta) term in 2D rotation matrix
        val theta = (atan2(plusSinTerm, plusCosTerm) + PI2) % PI2

        //dispatchToDisplayCartesian(observedPointsMap)
        // uncomment to display original points:
        //debugDisplay?.updateChart(MarkerManager.getMarkerPlot())

        val x = t.getEntry(0, 0)
        val y = t.getEntry(1, 0)
        val transform = Transform2D(Vector2(x, y), theta)
        displayAgentTransform(transform)
        return transform
    }

    /**
     * Converts an object in relative polar coordinates to one in absolute cartesian coordinates.
     * @param agent the localised position of the agent, calculated with [performLocalisation]
     */
    fun correctPolarObservation(observationPolar: ObjectObservationPolar, agent: Vector2): Vector2 {
        val out = Vector2(0.0, 0.0)
        out.x = observationPolar.distance * cos(observationPolar.angle * DEG_RAD)
        out.y = observationPolar.distance * sin(observationPolar.angle * DEG_RAD)
        out.add(agent)
        return out
    }

    /**
     * Imitates numpy array broadcasting for the particular case we need in the ICP localisation (mainly subtracting),
     * that is, broadcasting a 1x2 matrix to a Nx2 matrix.
     * @param matrix the input matrix to be broadcasted, of size 1x2
     * @param wantedRows number of rows in output matrix (to be duplicated)
     */
    private fun matrixBroadcast(matrix: RealMatrix, wantedRows: Int): RealMatrix {
        val out = MatrixUtils.createRealMatrix(wantedRows, matrix.columnDimension)
        for (i in 0 until wantedRows){
            val onlyRow = matrix.getRow(0)
            out.setRow(i, onlyRow)
        }
        return out
    }

    /**
     * Converts the map of flags in polar coordinates to relative cartesian coordinates.
     * Returns a sorted map to ensure correlation works correctly for best fit transform
     */
    private fun makeCartesian(points: Map<String, ObjectObservationPolar>): SortedMap<String, Vector2> {
        val out = sortedMapOf<String, Vector2>()
        for (entry in points){
            val point = entry.value
            // note: by this point, angles should already be 0 to 360
            // just do the old r*cos(theta), r*sin(theta) to convert from polar to cartesian
            val x = point.distance * cos(point.angle * DEG_RAD)
            val y = point.distance * sin(point.angle * DEG_RAD)
            out[entry.key] = Vector2(x, y)
        }
        return out
    }

    /** Converts a list of points into a matrix. The matrix will have points.size rows and 2 columns (since it's 2D). */
    private fun makeMatrix(points: Collection<Vector2>): RealMatrix {
        // like: [[x,y], [x,y]]
        val mat = MatrixUtils.createRealMatrix(points.size, 2)
        for ((i, point) in points.withIndex()){
            mat.setRow(i, doubleArrayOf(point.x, point.y))
        }
        return mat
    }

    /** Displays a graph of flag observations in polar form */
    private fun displayPolarFlags(observations: Map<String, ObjectObservationPolar>){
        val dataset = XYSeriesCollection()
        val series = XYSeries("Flags")
        for (observation in observations){
            series.add(observation.value.angle, observation.value.distance)
        }
        dataset.addSeries(series)
        val chart = ChartFactory.createPolarChart("Flag Observations", dataset, false, false, false)
        DEBUG_DISPLAY?.updateChart(chart)
    }

    /** Displays the position and orientation of a single agent on the debug display */
    private fun displayAgentTransform(transform: Transform2D){
        val dataset = XYSeriesCollection()
        val series = XYSeries("Agent")
        series.add(transform.pos.x, transform.pos.y)
        dataset.addSeries(series)
        val chart = ChartFactory.createScatterPlot("Agent Pos (angle=${transform.theta * RAD_DEG} deg)",
            "X", "Y", dataset)
        chart.xyPlot.domainAxis.setRange(-52.0, 52.0)
        chart.xyPlot.rangeAxis.setRange(-34.0, 34.0)
        DEBUG_DISPLAY?.updateChart(chart)
    }

    /** Displays a graph of flag observations in cartesian form */
    private fun displayCartesianFlags(observations: Map<String, Vector2>){
        val dataset = XYSeriesCollection()
        val series = XYSeries("Flags")
        for (observation in observations){
            series.add(observation.value.x, observation.value.y)
        }
        dataset.addSeries(series)
        val chart = ChartFactory.createScatterPlot("Flag Observations", "X", "Y", dataset)
        chart.xyPlot.domainAxis.setRange(-5.0, 100.0)
        chart.xyPlot.rangeAxis.setRange(-100.0, 100.0)
        DEBUG_DISPLAY?.updateChart(chart)
    }
}