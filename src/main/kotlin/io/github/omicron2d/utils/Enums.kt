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
 * Field positions in the 4-3-3 formation. This will be used by the behaviour and movement planners.
 * Sources:
 * - https://www.liveabout.com/offensive-the-4-3-3-formation-3557643
 * - http://www.soccercoachingpro.com/4-3-3-formation/
 * - https://www.active.com/soccer/articles/4-3-3-team
 * - https://en.wikipedia.org/wiki/Formation_(association_football)#4%E2%80%933%E2%80%933
 **/
enum class Roles433 {
    // Defence
    LEFT_BACK, // 1
    CENTRE_BACK_LEFT, // 2
    CENTRE_BACK_RIGHT, // 3
    RIGHT_BACK, // 4

    // Centre
    CENTRE_MID_LEFT, // 5
    CENTRE_MID_RIGHT, // 6
    CENTRE_MID_CENTRE, // 7

    // Attack
    STRIKER, // 8, aka centre forward
    LEFT_WING, // 9
    RIGHT_WING, // 10,

    // Goal keeper
    KEEPER, // 11
}

enum class PlayerRoles {
    DEFENDER,
    CENTRE_MID,
    STRIKER,
    LEFT_WING,
    RIGHT_WING,
    KEEPER
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

enum class ObjectType {
    UNKNOWN,
    FLAG,
    BALL,
    PLAYER,
    LINE,
    GOAL
}

enum class ViewMode {
    UNKNOWN,
    NARROW,
    NORMAL,
    WIDE
}

enum class ViewQuality {
    UNKNOWN,
    HIGH,
    LOW
}

enum class BehaviourStatus {
    RUNNING,
    SUCCESS,
    FAILURE,
}

// Sourced from rcsserver, src/types.h, variable PLAYMODE_STRINGS
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