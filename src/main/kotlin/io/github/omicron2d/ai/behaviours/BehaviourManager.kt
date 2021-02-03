/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.EPSILON
import org.tinylog.kotlin.Logger

/**
 * This class manages the agent's behaviours (what it's doing and how it's doing it).
 */
class BehaviourManager {
    private var currentMovement: MovementBehaviour = NullMovementBehaviour()
    private var currentComms: CommunicationBehaviour = NullCommunicationBehaviour()

    /**
     * Sets the current movement behaviour in the BehaviourManager. Handles exit and enter calls.
     */
    fun changeMovementBehaviour(newBehaviour: MovementBehaviour, ctx: AgentContext){
        Logger.debug("Changing movement behaviour to: ${newBehaviour.javaClass.simpleName}")
        currentMovement.onExit(ctx)
        currentMovement = newBehaviour
        currentMovement.onEnter(ctx)
    }

    /**
     * Calculates steering for the agent using the current steering behaviour. Returns a [MovementResult] with
     * either turn or dash non-null (but never both).
     */
    fun updateMovement(ctx: AgentContext): MovementResult {
        val steering = currentMovement.calculateSteering(ctx)
        val turn = currentMovement.calculateTurn(ctx)

        // FIXME WHAT DO WE DO IF THE BEHAVIOUR HAS FINISHED!!!!!!!!
        // we need like a behaviour queue don't we

        return if (turn <= EPSILON){
            // turn is zero, must be movement then
            MovementResult(dash = steering)
        } else {
            // turn is not zero, it's therefore going to be a movement behaviour
            MovementResult(turn = turn)
        }
    }

    /**
     * Calculates the communication message for this agent as a byte array. Result must be encoded to an rcssserver
     * compatible string using another strategy. If output size is zero, the behaviour has decided no message
     * should be sent.
     */
    fun updateCommunications(ctx: AgentContext): ByteArray {
        return currentComms.getBytes(ctx)
    }
}