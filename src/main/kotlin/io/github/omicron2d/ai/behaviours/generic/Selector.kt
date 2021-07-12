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
 * Behaviour tree node that executes its children in order until one succeeds. It will only fail if all children fail.
 */
class Selector : Behaviour() {
    /** copy of this node's children */
    private var internalChildren = LinkedList<Behaviour>()
    /** current behaviour being executed, first acquired from the head of the queue */
    private var currentChild: Behaviour? = null

    override fun onEnter(ctx: AgentContext) {
        internalChildren = children
        currentChild = internalChildren.remove()
        currentChild!!.onEnter(ctx)
    }

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        if (currentChild == null){
            Logger.error("Internal child should not be null in update!")
            return BehaviourStatus.FAILURE
        }

        val status = currentChild!!.onUpdate(ctx)
        if (status == BehaviourStatus.FAILURE){
            // current child has failed, that's ok because we're a selector, just get next node from queue
            currentChild?.onExit(ctx)
            // if next child is null, all children must have failed, so we have failed too
            val nextChild = internalChildren.poll() ?: return BehaviourStatus.FAILURE
            currentChild = nextChild
            currentChild?.onEnter(ctx)
        } else if (status == BehaviourStatus.SUCCESS){
            // found a successful node, we can return immediately
            return BehaviourStatus.SUCCESS
        }

        // otherwise, we continue on
        return BehaviourStatus.RUNNING
    }

    override fun toString(): String {
        return "Selector(currentChild=$currentChild, children=${children})"
    }
}