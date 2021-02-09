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
import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.PlayerObject
import io.github.omicron2d.utils.Side

/**
 * The HighLevelWorldModel contains information processed from the low level world model into more refined and useful data,
 * such as the agent's actual position
 * @param ball Absolute position of ball in field coords
 * @param teamPlayers Player list for players on our team. All access should be 0-indexed (ID not unum)
 * @param opponentPlayers Player list for players on the opposition team. All access should be 0-indexed (ID not unum)
 * @param unknownTeamPlayers List of players who we can see, but we do not know the team of
 * @param unknownPlayers List of players who we can see, but we don't know the team name NOR unum of
 * @param selfId The ID of ourselves. ID is the same as unum, but zero indexed (so just -1 on the unum)
 * @param selfSide Our side
 * @param playMode current play mode
 */
data class HighLevelWorldModel(
    var ball: BallObject = BallObject(),
    // note here that unum = index + 1 because unum is one indexed
    val teamPlayers: Array<PlayerObject> = Array(11) { PlayerObject(unum=it + 1, id=it) },
    val opponentPlayers: Array<PlayerObject> = Array(11) { PlayerObject(unum=it + 1, id=it) },
    val unknownTeamPlayers: MutableList<PlayerObject> = mutableListOf(),
    val unknownPlayers: MutableList<PlayerObject> = mutableListOf(),
    var selfId: Int = -1,
    var selfSide: Side = Side.UNKNOWN,
    var playMode: PlayMode = PlayMode.UNKNOWN
) : WorldModel {
    /** @return the player representing the current agent */
    fun getSelfPlayer(): PlayerObject {
        if (selfId == -1) throw IllegalStateException("Self ID is not currently known!")
        return teamPlayers[selfId]
    }
}