/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus

/**
 * Movement behaviour which does nothing.
 */
class NullMovement : MovementBehaviour {
    override fun calculateSteering(ctx: AgentContext): Vector2 = Vector2(0.0, 0.0)

    override fun calculateTurn(ctx: AgentContext): Double = 0.0

    override fun reportStatus(ctx: AgentContext): BehaviourStatus = BehaviourStatus.RUNNING

    override fun toString(): String {
        return "NullMovement()"
    }
}

/**
 * Head behaviour which does nothing.
 */
class NullHead : HeadBehaviour {
    override fun calculateHeadTurn(ctx: AgentContext): Double = 0.0

    override fun reportStatus(ctx: AgentContext): BehaviourStatus = BehaviourStatus.RUNNING

    override fun toString(): String {
        return "NullHead()"
    }
}

/**
 * Communication behaviour which does nothing.
 */
class NullCommunication : CommunicationBehaviour {
    override fun getBytes(ctx: AgentContext): ByteArray = byteArrayOf()

    override fun reportStatus(ctx: AgentContext): BehaviourStatus = BehaviourStatus.RUNNING

    override fun toString(): String {
        return "NullCommunication()"
    }
}