/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.Side

/**
 * The LowLevel world model contains information directly output from the server and parsed from messages, for example,
 * a list of flags rather than the agent's position.
 */
data class LowLevelWorldModel(
    var unum: Int = -1,
    var side: Side = Side.UNKNOWN)
    : WorldModel