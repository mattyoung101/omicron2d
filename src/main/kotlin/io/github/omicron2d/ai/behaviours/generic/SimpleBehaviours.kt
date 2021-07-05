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
import io.github.omicron2d.utils.SoccerRole
import org.tinylog.kotlin.Logger

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

class Inverter(private val child: Behaviour? = null) : Behaviour() {
    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        return when (child?.onUpdate(ctx)) {
            BehaviourStatus.RUNNING -> BehaviourStatus.RUNNING
            BehaviourStatus.SUCCESS -> BehaviourStatus.FAILURE
            BehaviourStatus.FAILURE -> BehaviourStatus.SUCCESS
            null -> {
                Logger.error("Child node should not be null in update!")
                // I feel like this is dumb, but it shouldn't happen anyway. Maybe we should throw an exception instead?
                return BehaviourStatus.RUNNING
            }
        }
    }

    override fun toString(): String {
        return "Inverter(child=$child)"
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