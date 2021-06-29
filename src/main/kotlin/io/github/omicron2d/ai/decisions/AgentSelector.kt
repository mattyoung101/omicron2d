/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.decisions

import io.github.omicron2d.utils.AgentContext

/**
 * Class that can be used to select agents from the world model
 */
class AgentSelector(private val ctx: AgentContext) {
    /**
     * Returns a player who would be a good target to pass the ball to, after kick off
     */
    fun selectGoodKickOffReceiver(){
    }
}