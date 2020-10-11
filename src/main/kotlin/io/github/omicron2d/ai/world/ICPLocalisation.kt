/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import io.github.omicron2d.utils.DEG_RAD
import io.github.omicron2d.utils.createOnesMatrix
import io.github.omicron2d.utils.debugDisplay
import mikera.vectorz.Vector2
import org.apache.commons.math3.linear.MatrixUtils
import org.apache.commons.math3.linear.RealMatrix
import org.jfree.chart.ChartFactory
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import kotlin.math.cos
import kotlin.math.sin

/**
 * @param angle, must be converted to 0 to 360 (NOT -180 to 180 as is sent by server!!)
 */
data class FlagObservationPolar(val distance: Double, val angle: Double)

/**
 * Performs localisation by solving a 2D iterative closest point (ICP) problem, commonly used in point set registration
 * problems. The reason we can use it here is because the positions of all the markers are known, and essentially then our
 * problem is just to figure out the (x, y, theta) transform to map our observed markers positions to absolute marker
 * positions - thereby inferring our true position and orientation.
 *
 * This implementation of ICP is a port of [https://github.com/ClayFlannigan/icp], originally written in Python.
 */
object ICPLocalisation {
    /**
     * Performs the localisation. May block for a few milliseconds while the ICP algorithm iterates.
     * @param observations a hash map of flag names, and their respective angles and distances relative to the player
     */
    fun performLocalisation(observations: Map<String, FlagObservationPolar>): Vector2 {
        val pointsList = makeCartesian(observations)
        val points = makeMatrix(pointsList.values.toTypedArray())
        dispatchToDisplayCartesian(pointsList)

        // FIXME - we have a problem! it's never true that A.shape == B.shape because we don't have all markers!!
        // TODO display polar chart of markers, using jfreechart: https://www.boraji.com/jfreechart-polar-chart-example

        // get the number of dimensions (this should always be 2 in our case)
        val m = points.columnDimension
        val src = createOnesMatrix(m + 1, points.rowDimension)
        val dst = createOnesMatrix(m + 1, MarkerManager.markerMatrix.rowDimension)

        // NOTE: for ICP, we will use either the KdTree, QuadTree or StrTree from Java Topology Suite
        return Vector2(0.0 ,0.0)
    }

    /**
     * Converts an object in relative polar coordinates to one in absolute cartesian coordinates, once the localised
     * position of the agent is known.
     * @param agent the localised position of the agent, calculated with [performLocalisation]
     */
    fun correctPolarObservation(observationPolar: FlagObservationPolar, agent: Vector2): Vector2 {
        val out = Vector2(0.0, 0.0)
        out.x = observationPolar.distance * cos(observationPolar.angle * DEG_RAD)
        out.y = observationPolar.distance * sin(observationPolar.angle * DEG_RAD)
        out.add(agent)
        return out
    }

    /** Converts the map of flags in polar coordinates to relative cartesian coordinates */
    private fun makeCartesian(points: Map<String, FlagObservationPolar>): Map<String, Vector2> {
        val out = hashMapOf<String, Vector2>()
        for (entry in points){
            val point = entry.value
            // note: by this point, angles should already be 0 to 360
            val x = point.distance * cos(point.angle * DEG_RAD)
            val y = point.distance * sin(point.angle * DEG_RAD)
            out[entry.key] = Vector2(x, y)
        }
        return out
    }

    /** Converts a list of points into a matrix. The matrix will have points.size rows and 2 columns (since it's 2D). */
    private fun makeMatrix(points: Array<Vector2>): RealMatrix {
        // like: [[x,y], [x,y]]
        val mat = MatrixUtils.createRealMatrix(points.size, 2)
        for ((i, point) in points.withIndex()){
            mat.setRow(i, doubleArrayOf(point.x, point.y))
        }
        return mat
    }

    private fun dispatchToDisplayPolar(observations: Map<String, FlagObservationPolar>){
        val dataset = XYSeriesCollection()
        val series = XYSeries("Flags")
        for (observation in observations){
            series.add(observation.value.angle, observation.value.distance)
        }
        dataset.addSeries(series)
        val chart = ChartFactory.createPolarChart("Flag Observations", dataset, false, false, false)
        debugDisplay?.updateChart(chart)
    }

    private fun dispatchToDisplayCartesian(observations: Map<String, Vector2>){
        val dataset = XYSeriesCollection()
        val series = XYSeries("Flags")
        for (observation in observations){
            series.add(observation.value.x, observation.value.y)
        }
        dataset.addSeries(series)
        val chart = ChartFactory.createScatterPlot("Flag Observations", "X", "Y", dataset)
        chart.xyPlot.domainAxis.setRange(-5.0, 100.0)
        chart.xyPlot.rangeAxis.setRange(-100.0, 100.0)
        debugDisplay?.updateChart(chart)
    }
}