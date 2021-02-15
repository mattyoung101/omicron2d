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
 * Manages head turning behaviours, like [MovementManager].
 * @param onQueueDepleted called to request a new head behaviour when the queue becomes empty
 */
class HeadManager(private val onQueueDepleted: (AgentContext) -> HeadBehaviour? = { null }) {
    var currentHead: HeadBehaviour = NullHead()
        private set

    /**
     * Queue of behaviours to be executed after the current one finishes. Switching between these is handled automatically
     * when [tickHead] is called.
     */
    val queue: Queue<HeadBehaviour> = LinkedList()

    /**
     * Sets the current head behaviour in the BehaviourManager. Handles exit and enter calls.
     */
    fun changeHead(newBehaviour: HeadBehaviour, ctx: AgentContext){
        Logger.debug("Setting behaviour to: $newBehaviour")
        currentHead.onExit(ctx)
        currentHead = newBehaviour
        currentHead.onEnter(ctx)
    }

    /**
     * Calculates steering for the agent using the current steering behaviour. Returns a double which is the number of
     * **radians** to add to the current head angle (or zero if not turning head).
     */
    fun tickHead(ctx: AgentContext): Double {
        currentHead.onUpdate(ctx)
        val angle = currentHead.calculateHeadTurn(ctx)

        // if we have new items in the queue and are doing nothing, switch to the new behaviour
        if (currentHead is NullHead && queue.isNotEmpty()){
            Logger.debug("New behaviour queued while in NullBehaviour, switching to it!")
            changeHead(queue.remove(), ctx)
        }

        // check if the current behaviour is done
        val status = currentHead.reportStatus(ctx)
        if (status != BehaviourStatus.RUNNING){
            if (status == BehaviourStatus.SUCCESS) {
                Logger.debug("Current behaviour $currentHead finished successfully")
            } else {
                Logger.warn("Current behaviour $currentHead has failed!")
            }

            if (queue.isEmpty()){
                Logger.debug("Queue depleted! Looking for next behaviour")

                // see if the event listener can get us a new behaviour
                val next = onQueueDepleted(ctx)
                if (next != null){
                    Logger.debug("Received valid behaviour from event listener: $next")
                    changeHead(next, ctx)
                } else {
                    Logger.warn("No more movement behaviours available! Reverting to NullBehaviour")
                    changeHead(NullHead(), ctx)
                }
            } else {
                Logger.debug("Fetching next behaviour from queue")
                changeHead(queue.remove(), ctx)
            }
        }

        return angle
    }
}