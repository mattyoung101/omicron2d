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
import org.tinylog.kotlin.Logger
import java.util.*

/**
 * Behaviour tree node that executes its children in a sequence. If one child fails, then this node fails too.
 */
class Sequence() : Behaviour() {
    /** list of children to execute */
    val children = LinkedList<Behaviour>()
    /** current behaviour being executed, first acquired from the head of the queue */
    private var currentChild: Behaviour? = null

    constructor(newChildren: Collection<Behaviour>) : this() {
        children.addAll(newChildren)
    }

    override fun onEnter(ctx: AgentContext) {
        currentChild = children.remove()
    }

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        if (currentChild == null){
            Logger.error("Current child should not be null!")
            return BehaviourStatus.RUNNING
        }

        val status = currentChild!!.onUpdate(ctx)
        if (status == BehaviourStatus.SUCCESS){
            // current child has finished, get next node from queue
            currentChild?.onExit(ctx)
            // if next child is null, sequence is finished
            val nextChild = children.poll() ?: return BehaviourStatus.SUCCESS
            currentChild = nextChild
            currentChild?.onEnter(ctx)
        } else if (status == BehaviourStatus.FAILURE){
            // escalate failure
            return BehaviourStatus.FAILURE
        }

        // otherwise, we continue on
        return BehaviourStatus.RUNNING
    }

    override fun toString(): String {
        return "Sequence(currentChild=$currentChild, children=${children.size} items)"
    }


}