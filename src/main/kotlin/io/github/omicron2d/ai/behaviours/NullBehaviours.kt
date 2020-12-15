/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import io.github.omicron2d.utils.AgentContext

/**
 * Movement behaviour which does nothing.
 */
class NullMovementBehaviour : MovementBehaviour {
    override fun getDashVelocity(): Double = 0.0

    override fun getTurnVelocity(): Double = 0.0

    // we are always willing to accept a new behaviour, so return that we are done
    override fun isDone(ctx: AgentContext): Boolean = true
}

/**
 * Head behaviour which does nothing
 */
class NullHeadBehaviour : HeadBehaviour {
    override fun getHeadVelocity(): Double = 0.0

    override fun isDone(ctx: AgentContext): Boolean = true
}