/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import com.badlogic.gdx.math.Vector2
import com.esotericsoftware.yamlbeans.YamlReader
import net.objecthunter.exp4j.ExpressionBuilder
import org.apache.commons.math3.linear.MatrixUtils
import org.jfree.chart.ChartFactory
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.tinylog.kotlin.Logger
import java.io.FileReader

/**
 * The job of the MarkerManager is to parse config_markers.yaml and handle requests for marker absolute positions.
 *
 * To improve performance, marker positions are stored as a Vector2D rather than a pair of exp4j expressions. This means
 * that if the field changes, you must call refreshMarkers() to update marker positions
 */
object MarkerManager {
    /** calculated coordinates for markers, refresh using refreshMarkers() */
    val markers = sortedMapOf<String, Vector2>()
    /** default values for variables on a standard field */
    private val variables = sortedMapOf(
        // default values sourced from: https://github.com/rcsoccersim/rcssserver/blob/master/src/serverparam.cpp#L130
        // TODO define these in general config
        Pair("pitch_half_w", 68.0 / 2.0),
        Pair("pitch_half_l", 105.0 / 2.0),
        Pair("penalty_l", 16.5),
        Pair("penalty_half_w", 40.32 / 2.0),
        Pair("goal_half_w", 18.32 / 2.0),
    )
    /** matrix of absolute marker coordinates, with 2 columns and the number of markers rows */
    val markerMatrix = MatrixUtils.createRealMatrix(55, 2)!!
    // note: if number of markers changes, please update number of rows above
    private var markersLoaded = false

    /**
     * Refresh marker coordinates by loading the YAML config file and re-calculating all the expressions.
     * You should call this function if the values of any of the variables (like goal_half_l) are changed somehow
     */
    fun reloadMarkers(){
        Logger.debug("Refreshing marker coordinates")
        val begin = System.currentTimeMillis().toDouble()

        val yamlReader = YamlReader(FileReader("config_markers.yml"))
        val yamlMarkers = yamlReader.read(Map::class.java)

        // parse marker expressions from yaml file, and add them to markers hashmap
        for (entry in yamlMarkers){
            val markerName = entry.key.toString()
            val pairs = entry.value.toString().split(",")
            val xstr = pairs[0].trim()
            val ystr = pairs[1].trim()
            Logger.trace("Parsing marker, name: $markerName, x expression: $xstr, y expression: $ystr")

            val xexp = ExpressionBuilder(xstr).variables(variables.keys).build().setVariables(variables)
            val yexp = ExpressionBuilder(ystr).variables(variables.keys).build().setVariables(variables)
            markers[markerName] = Vector2(xexp.evaluate(), yexp.evaluate())
        }

        // create marker matrix, basically a list of marker coordinates
        for ((i, marker) in markers.values.withIndex()){
            markerMatrix.setRow(i, doubleArrayOf(marker.x, marker.y))
        }

        Logger.debug("Marker refresh took ${System.currentTimeMillis().toDouble() - begin} ms (${markers.size} markers)")
        markersLoaded = true
    }

    init {
        // only load the markers once, shouldn't be too much of an issue
        if (!markersLoaded) reloadMarkers()
    }

    /**
     * Returns the coordinate for the requested marker
     * @param marker the marker ID, including its type. For example "f g r b".
     */
    fun getMarkerCoord(marker: String): Vector2 {
        return markers[marker] ?: throw IllegalArgumentException("No entry for marker \"$marker\"")
    }

    /**
     * Returns a plot of the positions of all the flags on the field
     */
    fun getMarkerPlot(): JFreeChart {
        val dataset = XYSeriesCollection()
        val series = XYSeries("Flags")
        for (marker in markers){
            series.add(marker.value.x, marker.value.y)
        }
        dataset.addSeries(series)
        return ChartFactory.createScatterPlot("Real Flag Positions", "X", "Y", dataset)
    }
}