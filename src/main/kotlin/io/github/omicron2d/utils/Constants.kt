/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import io.github.omicron2d.debug.DebugDisplay
import mikera.vectorz.Vector2
import kotlin.math.PI
import kotlin.random.Random

// This file holds constant, compile time defines that will not be edited
// General config should be stored in the *.properties files in the resources folder

/**
 * Version history:
 * 0.0.0-alpha: initial version
 * 0.1.0-alpha: major rewrite and refactor
 * 0.2.0-alpha: rewrite parsers in ANTLR
 * 0.3.0-alpha: localisation implemented, more ANTLR parsers, debug UI, storage now in YAML
 * 0.4.0-alpha: formation loading, high level world model
 * 0.5.0-alpha: (WIP) basic planning and movement
 */
const val OMICRON2D_VERSION = "0.5.0-alpha"
/** Supported rcssserver protocol version */
const val SERVER_PROTOCOL_VERSION = "15"
/** Charset available for the (say) command */
const val SAY_CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890().+*/?<>_ "
/** Charset available for team names, source is rcssserver serverparam.cpp check_teamname_format() */
const val TEAM_NAME_CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-_"
/** Mainly used for agent constructors, default port to connect to rcssserver with */
const val DEFAULT_PLAYER_PORT = 6000

/** Multiply to convert degrees to radians */
const val DEG_RAD = 0.017453292519943295
/** Multiply to convert radians to degrees */
const val RAD_DEG = 57.29577951308232
/** PI * 2 **/
const val PI2 = PI * 2.0
/** Used for floating point comparisons */
const val EPSILON = 0.0001
val ZERO_VECTOR = Vector2(0.0, 0.0)

// THREAD LOCAL VARIABLES
/**
 * Shared instance of GeneralConfig loaded from YAML at boot. DO NOT MODIFY AFTER DESERIALISATION!!!
 * When doing multi agent execution with TeamMain, each thread has its own copy of this.
 */
var CURRENT_CONFIG = ThreadLocal.withInitial { GeneralConfig() }!!
/** Shared debug display. Disabled during multi agent execution with TeamMain. MAY BE NULL!! BE CAREFUL! */
var DEBUG_DISPLAY: DebugDisplay? = null
val AGENT_STATS = ThreadLocal.withInitial { AgentStats() }!!
/** This is sort of a very hacky ThreadLocalRandom which gives each threaded a seeded Kotlin random for reproducibility */
val RAND = ThreadLocal.withInitial { Random(10242048) }!!