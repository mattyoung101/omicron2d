/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import io.github.omicron2d.debug.DebugDrawable
import io.github.omicron2d.utils.DEG_RAD
import io.github.omicron2d.utils.createOnesMatrix
import mikera.vectorz.Vector2
import org.apache.commons.math3.linear.MatrixUtils
import org.apache.commons.math3.linear.RealMatrix
import kotlin.math.cos
import kotlin.math.sin

/**
 * @param angle sent from server in degrees, -180 to 180
 */
data class FlagObservationPolar(val distance: Double, val angle: Double)

/**
 * Performs localisation by solving a 2D iterative closest point (ICP) problem, commonly used in point set registration
 * problems. The reason we can use it here is because we have a known layout of the field, and essentially then our
 * problem is just to figure out the (x, y, theta) transform to map our observations to the known field layout - thereby
 * inferring our true position.
 *
 * This implementation of ICP is a port of [https://github.com/ClayFlannigan/icp], originally written in Python.
 */
object ICPLocalisation : DebugDrawable {
    /** Converts the degree value, from -180 to 180 (as sent by server) to a proper radians value */
    private fun toRadians(deg: Double): Double {
        val degMod = (deg + 360.0) % 360.0
        return degMod * DEG_RAD
    }

    /** Converts the map of flags in polar coordinates to relative cartesian coordinates */
    private fun makeCartesian(points: Map<String, FlagObservationPolar>): Map<String, Vector2> {
        val out = hashMapOf<String, Vector2>()
        for (entry in points){
            val point = entry.value
            val x = point.distance * cos(toRadians(point.angle))
            val y = point.distance * sin(toRadians(point.angle))
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

    /**
     * Converts an object in relative polar coordinates to one in absolute cartesian coordinates, once the localised
     * position of the agent is known.
     * @param agent the localised position of the agent, calculated with [performLocalisation]
     */
    fun correctPolarObservation(observationPolar: FlagObservationPolar, agent: Vector2): Vector2 {
        val out = Vector2(0.0, 0.0)
        out.x = observationPolar.distance * cos(toRadians(observationPolar.angle))
        out.y = observationPolar.distance * sin(toRadians(observationPolar.angle))
        out.add(agent)
        return out
    }

    /**
     * Performs the localisation. May block for a few milliseconds while the ICP algorithm iterates.
     * @param observations a hash map of flag names, and their respective angles and distances relative to the player
     */
    fun performLocalisation(observations: Map<String, FlagObservationPolar>): Vector2 {
        val pointsList = makeCartesian(observations)
        val points = makeMatrix(pointsList.values.toTypedArray())

        // FIXME - we have a problem! it's never true that A.shape == B.shape because we don't have all markers!!
        // TODO display polar chart of markers, using jfreechart: https://www.boraji.com/jfreechart-polar-chart-example

        // get the number of dimensions (this should always be 2 in our case)
        val m = points.columnDimension
        val src = createOnesMatrix(m + 1, points.rowDimension)
        val dst = createOnesMatrix(m + 1, MarkerManager.markerMatrix.rowDimension)

        //println(MarkerManager.markerMatrix)

        // NOTE: for ICP, we will use either the KdTree, QuadTree or StrTree from Java Topology Suite
        return Vector2(0.0 ,0.0)
    }
}