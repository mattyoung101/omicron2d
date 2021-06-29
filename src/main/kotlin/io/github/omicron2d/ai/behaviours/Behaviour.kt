/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus

/**
 * Root class for all behaviours. A behaviour is essentially what the robot "does".
 *
 * A planning algorithm will decide which behaviours to execute in some sort of sequence, to achieve an overall aim
 * like "score a goal".
 *
 * The robot can execute one movement behaviour and one communication behaviour at a time.
 */
interface Behaviour {
    /** Called when the behaviour is started. Default method does nothing. */
    fun onEnter(ctx: AgentContext){}

    /** Called just before the behaviour specific manager requests info, e.g. just before calculateSteering(). */
    fun onUpdate(ctx: AgentContext){}

    /** Called when the behaviour is exited. Default does nothing. */
    fun onExit(ctx: AgentContext){}

    /** @return the current status of this behaviour (if it has finished, failed, etc) */
    fun reportStatus(ctx: AgentContext): BehaviourStatus
}

/**
 * A behaviour which controls the agent's movement by controlling dash speed and turn angle.
 * We group together dash and turn angle because they are most often necessary together.
 */
interface MovementBehaviour : Behaviour {
    /** @return dash velocity. x = amount of stamina to use, y = direction to go in, in **radians** (polar dash vector) */
    fun calculateSteering(ctx: AgentContext): Vector2

    /** @return amount of **radians** to add to current body angle */
    fun calculateTurn(ctx: AgentContext): Double
}

/**
 * A behaviour which controls the agent's head orientation. Separate from MovementBehaviour since rarely used
 */
interface HeadBehaviour : Behaviour {
    /** @return number of **radians** to add to current head angle (turn_neck command) */
    fun calculateHeadTurn(ctx: AgentContext): Double
}

/**
 * A behaviour which controls the agent's communication (say command)
 */
interface CommunicationBehaviour : Behaviour {
    /** @return the encoded bytes of the message */
    fun getBytes(ctx: AgentContext): ByteArray
}

/**
 * Encapsulates the result of a MovementBehaviour. If both dash and turn are set, it is expected that [dash] contains
 * only a power value (the server does not allow turning and angular dashes at the same time).
 * @param dash values in order for the dash command, or zero if not dashing
 * @param turn amount of **radians** to turn or zero if not turning
 */
data class MovementResult(val dash: Vector2, val turn: Double)