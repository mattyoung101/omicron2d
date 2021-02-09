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
import io.github.omicron2d.utils.BehaviourStatus
import org.tinylog.kotlin.Logger
import java.util.*

/**
 * This class manages the agent's steering behaviours (dashing, body angle, head angle).
 * @param onQueueDepleted called to request a new movement behaviour when the queue becomes empty
 */
class MovementManager(private val onQueueDepleted: (AgentContext) -> MovementBehaviour? = { null }) {
    private var currentMovement: MovementBehaviour = NullMovement()

    /**
     * Queue of behaviours to be executed after the current one finishes. Switching between these is handled automatically
     * when [tickMovement] is called.
     */
    val queue: Queue<MovementBehaviour> = LinkedList()

    /**
     * Sets the current movement behaviour in the BehaviourManager. Handles exit and enter calls.
     */
    fun setMovement(newBehaviour: MovementBehaviour, ctx: AgentContext){
        Logger.debug("Setting behaviour to: $newBehaviour")
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
        if (currentMovement::class.java == NullMovement::class.java && queue.isNotEmpty()){
            Logger.debug("New behaviour queued while in NullBehaviour, switching to it!")
            setMovement(queue.remove(), ctx)
        }

        // check if the current behaviour is done
        val status = currentMovement.reportStatus(ctx)
        if (status != BehaviourStatus.RUNNING){
            if (status == BehaviourStatus.SUCCESS) {
                Logger.debug("Current behaviour $currentMovement finished successfully")
            } else {
                Logger.warn("Current behaviour $currentMovement has failed!")
            }

            if (queue.isEmpty()){
                Logger.debug("Queue depleted! Looking for next behaviour")

                // see if the event listener can get us a new behaviour
                val next = onQueueDepleted(ctx)
                if (next != null){
                    Logger.debug("Received valid behaviour from event listener: $next")
                    setMovement(next, ctx)
                } else {
                    Logger.warn("No more movement behaviours available! Reverting to NullBehaviour")
                    setMovement(NullMovement(), ctx)
                }
            } else {
                Logger.debug("Fetching next behaviour from queue")
                setMovement(queue.remove(), ctx)
            }
        }

        return MovementResult(dash = steering, turn = turn)
    }
}