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
import mikera.vectorz.Vector2
import kotlin.math.cos
import kotlin.math.sin

/**
 * @param angle sent from server in degrees, -180 to 180
 */
data class FlagObservationPolar(val distance: Double, val angle: Double)

/**
 * Performs localisation by solving a 2D iterative closest point (ICP) problem, which is sort of similar to least squares
 * type thing.
 */
object ICPLocalisation {
    /** Converts the degree value, from -180 to 180 (as sent by server) to a proper radians value */
    private fun toRadians(deg: Double): Double {
        val deg2 = (deg + 360.0) % 360.0
        return deg2 * DEG_RAD
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

    /**
     * Converts an object in relative polar coordinates to one in absolute cartesian coordinates, once the localised
     * position of the agent is known.
     */
    fun correctPolarObservation(observationPolar: FlagObservationPolar, agent: Vector2): Vector2 {
        // TEMP
        return Vector2(0.0, 0.0)
    }

    /**
     * Performs the localisation. May block for a few milliseconds while the ICP algorithm iterates.
     * @param points a hash map of flag names, and their respective angles and distances relative to the player
     */
    fun localise(points: Map<String, FlagObservationPolar>): Vector2 {
        val cartesian = makeCartesian(points)
        // NOTE: for ICP, we will use either the KdTree, QuadTree or StrTree from Java Topology Suite
        return Vector2(0.0 ,0.0)
    }
}