/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.lowlevel

import io.github.omicron2d.ai.behaviours.Behaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus

/**
 * This behaviour makes the agent sit still for a certain number of milliseconds.
 * @param millis number of milliseconds to wait for
 */
class Wait(private val millis: Int) : Behaviour() {
    private var startTime = 0L

    override fun onEnter(ctx: AgentContext) {
        startTime = System.currentTimeMillis()
    }

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return if (System.currentTimeMillis() - startTime > millis) BehaviourStatus.SUCCESS else BehaviourStatus.RUNNING
    }

    override fun toString(): String {
        return "Wait(millis=$millis)"
    }


}