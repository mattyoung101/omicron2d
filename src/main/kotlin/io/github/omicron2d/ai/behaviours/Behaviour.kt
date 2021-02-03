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
import mikera.vectorz.Vector2

// consider renaming this class and package to "skills"?? would that fit better?

// FIXME Important note: we CANNOT turn while driving! Can only turn when not issuing a dash command!
// This means that probably drive and orient behaviours need to be separate
// TODO maybe consider bringing back head turning for when we're driving around so we can scan a little?

/**
 * Root class for all behaviours. A behaviour is essentially what the robot "does".
 *
 * The GOAP planner will choose what behaviours we should execute in which order to succeed at our goal. We will
 * probably give the planner access to all behaviours, low level ones such as ScanWorld and high level ones such as
 * DefendGoal. We hope that the planner is smart enough to figure everything out by itself.
 *
 * The robot can execute one movement behaviour and one communication behaviour at a time.
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

/**
 * A task which controls the agent's movement by controlling dash speed and turn angle.
 * We group together dash and turn angle because they are most often necessary together.
 */
interface MovementBehaviour : Behaviour {
    /** @return dash velocity. x = amount of stamina to use, y = direction to go in, in **radians** (polar dash vector) */
    fun calculateSteering(ctx: AgentContext): Vector2

    /** @return amount of **radians** to add to current body angle */
    fun calculateTurn(ctx: AgentContext): Double
}

/**
 * A task which controls the agent's communication (say command)
 */
interface CommunicationBehaviour : Behaviour {
    /** @return the encoded bytes of the message (will be enco */
    fun getBytes(ctx: AgentContext): ByteArray
}

/**
 * Encapsulates the result of a MovementBehaviour. Only either [dash] or [turn] should be non-null.
 * This means the robot is either turning or dashing (we cannot do both at the same time apparently).
 * @param dash if not null, indicates we are dashing and the values in order for the dash command
 * @param turn if not null, indicates we are issuing a turn and the amount of **radians** to turn
 */
data class MovementResult(val dash: Vector2? = null, val turn: Double? = null)

///**
// * A task which controls the agent's head orientation
// */
//@Deprecated("Due to the fact that we can dash in any direction now, turning the head appears to be useless.")
//interface HeadBehaviour : Behaviour {
//    /**
//     * @return number of **radians** to add to current head angle (turn_neck command)
//     */
//    fun getHeadVelocity(): Double
//}