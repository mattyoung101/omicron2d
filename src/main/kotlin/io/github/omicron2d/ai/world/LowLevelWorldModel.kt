/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import io.github.omicron2d.communication.messages.SeeObject
import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.Side

/**
 * The LowLevel world model contains information directly received from the server and parsed from messages, for example,
 * a list of flags rather than the agent's position.
 * @param selfSide which side this agent is on
 * @param selfUnum the uniform number of ourselves, as received by the server. This is one indexed, unlike ID in
 * HighLevelWorldModel.
 * @param selfIsGoalie true if we are the goalie
 * @param goodFlags list of flags we can actually feed to the localiser, excluding all the weird ones (behind, etc)
 * @param allFlags all flags, including bad/weird ones AND ones that we give to the localiser
 * @param players list of seen objects who are players (regardless of info)
 * @param ball the ball if we see it, otherwise null
 * @param time server time as received by a few messages
 */
data class LowLevelWorldModel(
    var selfSide: Side = Side.UNKNOWN,
    var selfUnum: Int = -1,
    var selfIsGoalie: Boolean = false,
    var goodFlags: List<SeeObject> = listOf(),
    var allFlags: List<SeeObject> = listOf(),
    var players: List<SeeObject> = listOf(),
    var ball: SeeObject? = null,
    var time: Int = -1
) : WorldModel