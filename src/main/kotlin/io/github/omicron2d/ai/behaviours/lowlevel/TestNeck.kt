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
import io.github.omicron2d.utils.RAND

class TestNeck : Behaviour() {
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        ctx.neckResult = RAND.get().nextDouble(-180.0, 180.0)
        return BehaviourStatus.RUNNING
    }
}