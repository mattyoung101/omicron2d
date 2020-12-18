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

// FIXME rename this class and package to "skills"

/*
 * Problems with this approach:
 * - one server action, like (turn) corresponds to both MovementBehaviour AND OrientationBehaviour, so which behaviour
 * gets to choose which way to spin?
 * - how do we represent behaviours who need both movement and orientation?
 * - how do we get head and body to work together?
 *
 * NOTE: we can kind of think of each Behaviour as a state in a state machine almost?
 */

/**
 * Root class for all behaviours. A behaviour is essentially what the robot "does".
 *
 * The GOAP planner will choose what behaviours we should execute in which order to succeed at our goal. We will
 * probably give the planner access to all behaviours, low level ones such as ScanWorld and high level ones such as
 * DefendGoal. We hope that the planner is smart enough to figure everything out by itself.
 *
 * The robot can generally have and execute one behaviour for each of its models.
 * In other words, we have one movement behaviour, one orientation and one communication behaviour executing at a time.
 */
interface Behaviour {
    /** Called when the behaviour is started. Default method does nothing. */
    fun onEnter(ctx: AgentContext){}

    // Note that there is no onUpdate method, because each implementing Behaviour has its own methods for getting
    // specific info related to itself (like getAgentVelocity)

    /** Called when the behaviour is exited. Default does nothing. */
    fun onExit(ctx: AgentContext){}

    /** @return if true, this Behaviour believes it has finished working, otherwise, it is still working. */
    fun isDone(ctx: AgentContext): Boolean
}

//interface ExclusiveBodyAngleControl {
//    /** @return if true, this behaviour needs exclusive control over body angle (turn command) */
//    fun isExclusiveBodyAngleControl(): Boolean
//}

/**
 * A task which controls the agent's movement by controlling dash speed and turn angle.
 * We group together dash and turn angle because they are most often necessary together.
 */
interface MovementBehaviour : Behaviour {
    /** @return amount of dash power to execute this tick */
    fun getDashVelocity(): Double

    /** @return amount of **radians** to add to current body angle (turn command) */
    fun getTurnVelocity(): Double
}

/**
 * A task which controls the agent's head orientation
 */
interface HeadBehaviour : Behaviour {
    /**
     * @return number of **radians** to add to current head angle (turn_neck command)
     */
    fun getHeadVelocity(): Double
}

/**
 * A task which controls the agent's communication (say command)
 */
interface CommunicationBehaviour : Behaviour
// TODO