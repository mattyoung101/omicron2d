package io.github.omicron2d

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

/**
 * Field positions in the 4-3-3 formation. This will be used by the behaviour and movement planners.
 * The keeper is not included because it's a separate agent. This is only for _player_ positions.
 * Sources:
 * - https://www.liveabout.com/offensive-the-4-3-3-formation-3557643
 * - http://www.soccercoachingpro.com/4-3-3-formation/
 * - https://www.active.com/soccer/articles/4-3-3-team
 * - https://en.wikipedia.org/wiki/Formation_(association_football)#4%E2%80%933%E2%80%933
 **/
enum class PlayerRoles {
    // Goal keeper
    KEEPER, // 0

    // Defence
    CENTRE_BACK_LEFT, // 1
    CENTRE_BACK_RIGHT, // 2
    FULL_BACK_LEFT, // 3
    FULL_BACK_RIGHT, // 4

    // Centre
    CENTRE_MID_LEFT, // 5
    CENTRE_MID_RIGHT, // 6
    CENTRE_MID_CENTRE, // 7

    // Attack
    STRIKER, // 8, aka centre forward
    LEFT_WING, // 9
    RIGHT_WING, // 10
}

/**
 * This enum is used by the movement executor to decide how much stamina to use when moving somewhere
 */
enum class NavigationUrgency {
    /** reaching this point is desirable within a very long timeframe, so don't use much stamina **/
    LOW,
    /** reaching this point is desirable within the next few ticks **/
    NORMAL,
    /** not reaching this point could miss a potential scoring opportunity **/
    HIGH,
    /** not reaching this point could concede a goal **/
    CRITICAL
}

enum class Side {
    UNKNOWN,
    LEFT,
    RIGHT,
}

// Sourced from Atan's PlayMode.java
enum class PlayMode {
    UNKNOWN,

    /**
     * The mode of a game before it starts.
     */
    BEFORE_KICK_OFF,
    /**
     * The time has finished.
     */
    TIME_OVER,
    /**
     * The default play mode.
     */
    PLAY_ON,
    /**
     * We get kick off!
     */
    KICK_OFF_OWN,
    /**
     * They get kick off.
     */
    KICK_OFF_OTHER,
    /**
     * We get a kick in.
     */
    KICK_IN_OWN,
    /**
     * They get a kick in.
     */
    KICK_IN_OTHER,
    /**
     * We get a free kick.
     */
    FREE_KICK_OWN,
    /**
     * They get a free kick.
     */
    FREE_KICK_OTHER,
    /**
     * We commited a free kick fault.
     */
    FREE_KICK_FAULT_OWN,
    /**
     * They commited a free kick fault.
     */
    FREE_KICK_FAULT_OTHER,
    /**
     * We get a corner kick.
     */
    CORNER_KICK_OWN,
    /**
     * They get a corner kick.
     */
    CORNER_KICK_OTHER,
    /**
     * We get a goal kick.
     */
    GOAL_KICK_OWN,
    /**
     * They get a goal kick.
     */
    GOAL_KICK_OTHER,
    /**
     * We scored!
     */
    GOAL_OWN,
    /**
     * They scored = (
     */
    GOAL_OTHER,
    /**
     * Kick off for the left team.
     */
    KICK_OFF_L,
    /**
     * Kick off for the right team.
     */
    KICK_OFF_R,
    /**
     * Kick in for the left team.
     */
    KICK_IN_L,
    /**
     * Kick in for the right team.
     */
    KICK_IN_R,
    /**
     * Free kick for the right team.
     */
    FREE_KICK_R,
    /**
     * Free kick for the left team.
     */
    FREE_KICK_L,
    /**
     * Free kick fault for the left team.
     */
    FREE_KICK_FAULT_L,
    /**
     * Free kick fault for the left team.
     */
    FREE_KICK_FAULT_R,
    /**
     * Corner kick for the right team.
     */
    CORNER_KICK_R,
    /**
     * Corner kick for the left team.
     */
    CORNER_KICK_L,
    /**
     * Goal kick for the right team.
     */
    GOAL_KICK_R,
    /**
     * Goal kick for the left team.
     */
    GOAL_KICK_L,
    /**
     * Right team scored.
     */
    GOAL_R,
    /**
     * Left team scored.
     */
    GOAL_L;
}