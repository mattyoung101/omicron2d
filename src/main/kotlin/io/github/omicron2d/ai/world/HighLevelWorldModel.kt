/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.world

import mikera.vectorz.Vector2

/**
 * The HighLevelWorldModel contains information processed from the low level world model into more refined and useful data,
 * such as the agent's actual position
 */
data class HighLevelWorldModel(
    /** localised agent position */
    var agentPos: Vector2 = Vector2.of(0.0, 0.0),
    /** localised ball position */
    var ballPos: Vector2 = Vector2.of(0.0, 0.0),
) : WorldModel