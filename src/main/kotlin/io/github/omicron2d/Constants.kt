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

enum class MessageSender {
    UNKNOWN,
    ONLINE_COACH_LEFT,
    ONLINE_COACH_RIGHT,
    COACH,
    REFEREE,
    SELF
}

// Sourced from Atan's PlayMode.java
enum class PlayMode {
    UNKNOWN,
    BEFORE_KICK_OFF,
    TIME_OVER,
    PLAY_ON,
    KICK_OFF_L,
    KICK_OFF_R,
    KICK_IN_L,
    KICK_IN_R,
    FREE_KICK_L,
    FREE_KICK_R,
    CORNER_KICK_L,
    CORNER_KICK_R,
    GOAL_KICK_L,
    GOAL_KICK_R,
    GOAL_L,
    GOAL_R,
    DROP_BALL,
    OFFSIDE_L,
    OFFSIDE_R,
    PENALTY_KICK_L,
    PENALTY_KICK_R,
    FIRST_HALF_OVER,
    PAUSE,
    HUMAN_JUDGE,
    FOUL_CHARGE_L,
    FOUL_CHARGE_R,
    FOUL_PUSH_L,
    FOUL_PUSH_R,
    FOUL_MULTIPLE_ATTACK_L,
    FOUL_MULTIPLE_ATTACK_R,
    FOUL_BALLOUT_L,
    FOUL_BALLOUT_R,
    BACK_PASS_L,
    BACK_PASS_R,
    FREE_KICK_FAULT_L,
    FREE_KICK_FAULT_R,
    CATCH_FAULT_L,
    CATCH_FAULT_R,
    INDIRECT_FREE_KICK_L,
    INDIRECT_FREE_KICK_R,
    PENALTY_SETUP_L,
    PENALTY_SETUP_R,
    PENALTY_READY_L,
    PENALTY_READY_R,
    PENALTY_TAKEN_L,
    PENALTY_TAKEN_R,
    PENALTY_MISS_L,
    PENALTY_MISS_R,
    PENALTY_SCORE_L,
    PENALTY_SCORE_R,
    ILLEGAL_DEFENSE_L,
    ILLEGAL_DEFENSE_R
}