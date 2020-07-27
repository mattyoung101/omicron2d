package io.github.omicron2d.utils

// This file holds constant, compile time defines that will not be edited
// General config should be stored in the *.properties files in the resources folder

/**
 * Version history:
 * 0.0.0a: initial version
 * 0.1.0a: (WIP) major rewrite
 */
const val VERSION = "0.1.0a"
/** Supported rcssserver protocol version */
const val SERVER_PROTOCOL_VERSION = "15"
/** Charset available for the (say) command */
const val SAY_CHARSET = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM().+*/?<>_ "

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