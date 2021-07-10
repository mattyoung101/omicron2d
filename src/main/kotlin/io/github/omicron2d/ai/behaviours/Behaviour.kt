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
import java.util.*

/**
 * Root class for all behaviours. A behaviour is essentially what the robot "does", and is also a node in a behaviour
 * tree. Different types of generic behaviours (such as Sequence, Selector, etc) will be used to organise execution of
 * movement/head/communication behaviours, to achieve an overall goal like "kick off".
 * This replaces my previous method of using a STRIPS planner, because I believe that is currently un-necessary.
 *
 * The robot can execute one movement behaviour and one communication behaviour at a time.
 */
abstract class Behaviour() {
    /** Called when the behaviour is started. Default method does nothing. */
    open fun onEnter(ctx: AgentContext){}

    /**
     * Called to update the behaviour. The behaviour must apply its changes to the passed [ctx] object.
     * @return status of the behaviour after executing
     */
    abstract fun onUpdate(ctx: AgentContext): BehaviourStatus

    /** Called when the behaviour is exited. Default does nothing. */
    open fun onExit(ctx: AgentContext){}

    /** List of children owned by this node */
    val children = LinkedList<Behaviour>()

    /** Constructor that adds the defined list of children */
    constructor(newChildren: Collection<Behaviour>) : this() {
        children.addAll(newChildren)
    }
}

// probably deprecated, we'll see
/**
 * A behaviour which controls the agent's communication (say command)
 */
abstract class CommunicationBehaviour : Behaviour() {
    /** @return the encoded bytes of the message */
    abstract fun getBytes(ctx: AgentContext): ByteArray
}