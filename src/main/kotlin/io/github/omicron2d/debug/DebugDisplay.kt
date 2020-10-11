/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.debug

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import java.awt.Dimension

/**
 * A mini-application, hosted within the main Omicron2D agent app, that can display JFreeChart graphs
 * Based on: [http://www.java2s.com/Code/Java/Chart/JFreeChartPolarChartDemo.htm]
 * and [https://www.boraji.com/jfreechart-polar-chart-example]
 */
class DebugDisplay : ApplicationFrame("Omicron2D Debug") {
    private var currentChart =
        ChartFactory.createPolarChart("Empty Chart", null, false, false, false).apply {
            // 4:3 aspect ratios: https://calculateaspectratio.com/4-3-calculator
            preferredSize = Dimension(800, 600)
        }
    private val panel = ChartPanel(currentChart)

    init {
        contentPane = panel
    }

    /**
     * Displays a new chart in the UI
     */
    fun updateChart(chart: JFreeChart){
        currentChart = chart
        panel.chart = currentChart
    }
}