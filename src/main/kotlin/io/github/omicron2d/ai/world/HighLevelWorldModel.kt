/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

@file:Suppress("ArrayInDataClass")

package io.github.omicron2d.ai.world

import io.github.omicron2d.utils.BallObject
import io.github.omicron2d.utils.PlayerObject
import io.github.omicron2d.utils.Side
import io.github.omicron2d.utils.Transform2D

// FIXME teamPlayers, opponentPlayers need to be one indexed!! since unum is one indexed!

/**
 * The HighLevelWorldModel contains information processed from the low level world model into more refined and useful data,
 * such as the agent's actual position
 * @param ball absolute position of ball in field coords
 * @param teamPlayers player list for players on our tea
 * @param opponentPlayers player list for players on the opposition team
 * @param unknownTeamPlayers list of players who we can see, but we do not know the team of
 * @param unknownPlayers list of players who we can see, but we don't know the team name NOR unum of
 * @param selfUnum the unum of ourselves
 * @param selfSide our side
 */
data class HighLevelWorldModel(
    var ball: BallObject = BallObject(),
    val teamPlayers: Array<PlayerObject> = Array(11) { PlayerObject(unum=it) },
    val opponentPlayers: Array<PlayerObject> = Array(11) { PlayerObject(unum=it) },
    val unknownTeamPlayers: MutableList<PlayerObject> = mutableListOf(),
    val unknownPlayers: MutableList<PlayerObject> = mutableListOf(),
    var selfUnum: Int = -1,
    var selfSide: Side = Side.UNKNOWN
) : WorldModel {
    fun getSelfPlayer(): PlayerObject {
        return teamPlayers[selfUnum]
    }
}