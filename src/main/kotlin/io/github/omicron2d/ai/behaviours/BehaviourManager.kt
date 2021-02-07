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
import java.util.*
import kotlin.math.abs

/**
 * This class manages the agent's behaviours (what it's doing and how it's doing it).
 * @param onMoveQueueDepleted called to request a new movement behaviour when the queue becomes empty
 */
class BehaviourManager(private val onMoveQueueDepleted: (AgentContext) -> MovementBehaviour? = { null }) {
    private var currentMovement: MovementBehaviour = NullMovement()
    private var currentComms: CommunicationBehaviour = NullCommunication()
    /**
     * Queue of behaviours to be executed after the current one finishes. Switching between these is handled automatically
     * when [tickMovement] is called.
     */
    val movementQueue: Queue<MovementBehaviour> = LinkedList()

    /**
     * Sets the current movement behaviour in the BehaviourManager. Handles exit and enter calls.
     */
    fun setMovementBehaviour(newBehaviour: MovementBehaviour, ctx: AgentContext){
        Logger.debug("Setting movement behaviour to: $newBehaviour")
        currentMovement.onExit(ctx)
        currentMovement = newBehaviour
        currentMovement.onEnter(ctx)
    }

    /**
     * Calculates steering for the agent using the current steering behaviour. Returns a [MovementResult] with
     * either turn or dash non-null (but never both).
     */
    fun tickMovement(ctx: AgentContext): MovementResult {
        val steering = currentMovement.calculateSteering(ctx)
        val turn = currentMovement.calculateTurn(ctx)

        // if we have new items in the queue and are doing nothing, switch to the new behaviour
        if (currentMovement::class.java == NullMovement::class.java && movementQueue.isNotEmpty()){
            Logger.info("New movement behaviour queued while in NullBehaviour, switching to it!")
            setMovementBehaviour(movementQueue.remove(), ctx)
        }

        // check if the current behaviour is done
        if (currentMovement.isDone(ctx)){
            Logger.debug("Current behaviour $currentMovement has finished")

            if (movementQueue.isEmpty()){
                Logger.debug("Movement queue depleted! Looking for next behaviour")

                // see if the event listener can get us a new behaviour
                val next = onMoveQueueDepleted(ctx)
                if (next != null){
                    Logger.debug("Received valid behaviour from event listener: $next")
                    setMovementBehaviour(next, ctx)
                } else {
                    Logger.warn("No more movement behaviours available! Reverting to NullBehaviour")
                    setMovementBehaviour(NullMovement(), ctx)
                }
            } else {
                Logger.debug("Fetching next behaviour from queue")
                setMovementBehaviour(movementQueue.remove(), ctx)
            }
        }

        return if (abs(turn) <= EPSILON){
            // turn is zero, must be movement then
            MovementResult(dash = steering)
        } else {
            // turn is not zero, it's therefore going to be a turn
            MovementResult(turn = turn)
        }
    }

    /**
     * Calculates the communication message for this agent as a byte array. Result must be encoded to an rcssserver
     * compatible string using another strategy. If output size is zero, the behaviour has decided no message
     * should be sent.
     */
    fun tickCommunications(ctx: AgentContext): ByteArray {
        return currentComms.getBytes(ctx)
    }
}