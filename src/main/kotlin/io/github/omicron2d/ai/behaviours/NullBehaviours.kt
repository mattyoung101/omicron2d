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
import mikera.vectorz.Vector2

/**
 * Movement behaviour which does nothing.
 */
class NullMovement : MovementBehaviour {
    override fun calculateSteering(ctx: AgentContext): Vector2 = Vector2(0.0, 0.0)

    override fun calculateTurn(ctx: AgentContext): Double = 0.0

    // we are always willing to accept a new behaviour, so return that we are done
    override fun isDone(ctx: AgentContext): Boolean = false

    override fun toString(): String {
        return "NullMovement()"
    }
}

/**
 * Communication behaviour which does nothing.
 */
class NullCommunication : CommunicationBehaviour {
    override fun getBytes(ctx: AgentContext): ByteArray = byteArrayOf()

    override fun isDone(ctx: AgentContext): Boolean = false

    override fun toString(): String {
        return "NullCommunication()"
    }
}