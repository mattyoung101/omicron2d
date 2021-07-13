/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.generic

import io.github.omicron2d.ai.behaviours.Behaviour
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.SoccerRole

// Class for behaviours with trivial implementations

class AlwaysSuccess : Behaviour() {
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return BehaviourStatus.SUCCESS
    }

    override fun toString(): String {
        return "AlwaysSuccess()"
    }
}

class AlwaysFailure : Behaviour() {
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return BehaviourStatus.FAILURE
    }

    override fun toString(): String {
        return "AlwaysFailure()"
    }
}

/** Succeeds iff the role of agent is equal to the one specified */
class AssertRole(private var role: SoccerRole = SoccerRole.UNKNOWN) : Behaviour(){
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return if (ctx.world.role == role){
            BehaviourStatus.SUCCESS
        } else {
            BehaviourStatus.FAILURE
        }
    }

    override fun toString(): String {
        return "AssertRole(role=$role)"
    }
}

/** Waits for the world playmode to match the one provided */
class AwaitPlayMode(private var playMode: PlayMode = PlayMode.UNKNOWN) : Behaviour(){
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return if (ctx.world.playMode == playMode) BehaviourStatus.SUCCESS else BehaviourStatus.FAILURE
    }

    override fun toString(): String {
        return "AwaitPlayMode(playMode=$playMode)"
    }
}