/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

/**
 * Deserialised by YAMLBeans, loaded from config_general.yml.
 * This is better documented in the YAML file itself.
 */
data class GeneralConfig(
    var teamName: String = "",
    var serverHost: String = "",
    var playerPort: Int = 0,
    var trainerPort: Int = 0,
    var coachPort: Int = 0,
    var timeout: Int = 0,
    var showDebugDisplay: Boolean = false,
    /** initial formation path */
    var initialFormation: String = "",
    var fieldLength: Double = 0.0,
    var fieldWidth: Double = 0.0,
    var listenToOpposition: Boolean = false,
    var moveKp: Double = 0.0,
    var moveKd: Double = 0.0,
    var movePointReachedThresh: Double = 0.0,
    var turnBodyToleranceDeg: Double = 0.0,
    var turnBodySmoothing: Double = 0.0,
)