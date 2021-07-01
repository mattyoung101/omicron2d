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
import io.github.omicron2d.utils.BehaviourStatus

/**
 * Behaviour which does nothing.
 */
class NullBehaviour : Behaviour() {
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return BehaviourStatus.RUNNING
    }

    override fun toString(): String {
        return "NullMovement()"
    }
}

/**
 * Communication behaviour which does nothing.
 */
class NullCommunication : CommunicationBehaviour() {
    override fun getBytes(ctx: AgentContext): ByteArray = byteArrayOf()

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return BehaviourStatus.RUNNING
    }

    override fun toString(): String {
        return "NullCommunication()"
    }
}