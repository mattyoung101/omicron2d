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

// TODO update docs to be @param

/**
 * The LowLevel world model contains information directly received from the server and parsed from messages, for example,
 * a list of flags rather than the agent's position.
 */
data class LowLevelWorldModel(
    var selfSide: Int = -1,
    var selfUnum: Side = Side.UNKNOWN,
    /** true if we are the goalie */
    var selfIsGoalie: Boolean = false,
    /** list of flags we can actually feed to the localiser, basically excludes all the weird ones */
    var goodFlags: List<SeeObject> = listOf(),
    /** all flags, including bad ones AND ones that we give to the localiser */
    var allFlags: List<SeeObject> = listOf(),
    var players: List<SeeObject> = listOf(),
    var ball: SeeObject? = null,
    /** server time as received by a few messages */
    var time: Int = -1
) : WorldModel