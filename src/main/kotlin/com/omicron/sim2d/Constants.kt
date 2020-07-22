package com.omicron.sim2d

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

enum class Side {
    LEFT,
    RIGHT
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