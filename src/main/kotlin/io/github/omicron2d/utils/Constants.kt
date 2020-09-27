/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

// This file holds constant, compile time defines that will not be edited
// General config should be stored in the *.properties files in the resources folder

/**
 * Version history:
 * 0.0.0-alpha: initial version
 * 0.1.0-alpha: major rewrite and refactor
 * 0.2.0-alpha: rewrite parsers in ANTLR
 * 0.3.0-alpha: (WIP)
 */
const val OMICRON2D_VERSION = "0.3.0-alpha"
/** Supported rcssserver protocol version */
const val SERVER_PROTOCOL_VERSION = "15"
/** Charset available for the (say) command */
const val SAY_CHARSET = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM().+*/?<>_ "
/** Charset available for team names, source is rcssserver serverparam.cpp check_teamname_format() */
const val TEAM_NAME_CHARSET = "+-_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
const val DEFAULT_PLAYER_PORT = 6000
var currentConfig = GeneralConfig()

// Source: https://github.com/rcsoccersim/rcssserver/blob/master/src/serverparam.cpp
// in our translation, length = width and width = height (effectively instead of length x width it's width x height)
const val FIELD_WIDTH = 105.0
const val FIELD_HEIGHT = 68.0
const val FIELD_DIAGONAL = 125.1
const val PENALTY_AREA_WIDTH = 16.5
const val PENALTY_AREA_HEIGHT = 40.32
const val GOAL_AREA_WIDTH = 5.5
const val GOAL_AREA_HEIGHT = 18.32
const val GOAL_WIDTH = 14.02