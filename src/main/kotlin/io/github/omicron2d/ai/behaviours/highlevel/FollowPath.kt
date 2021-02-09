/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.highlevel

import com.badlogic.gdx.math.Vector2
import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.ai.behaviours.MovementManager
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPoint
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPointLooking
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import org.tinylog.kotlin.Logger
import java.util.*

/**
 * Follows a path of points. Internally dispatches to a series of MoveToPoint behaviours.
 * TODO make this use a child MovementManager?
 * @param path list of points to visit
 * @param stamina for each point in the path array, the max amount of stamina to use when navigating to this point
 * @param looking if true, uses [MoveToPointLooking] instead of [MoveToPoint]
 */
class FollowPath(private val path: Array<Vector2>, private val stamina: DoubleArray, private val looking: Boolean) : MovementBehaviour {
    /** queue of MoveToPoint behaviours to execute */
    private val queue: Queue<MovementBehaviour> = LinkedList()
    /** current movement behaviour we are executing, or null if none */
    private var currentMove: MovementBehaviour? = null
    private var status = BehaviourStatus.RUNNING
    private var manager = MovementManager()

    init {
        if (path.size != stamina.size){
            Logger.error("Path array and stamina array size mismatch")
            status = BehaviourStatus.FAILURE
        } else if (path.isEmpty() || stamina.isEmpty()){
            Logger.error("Path and stamina arrays must not be empty")
            status = BehaviourStatus.FAILURE
        }

        for ((i, point) in path.withIndex()){
            if (looking){
                queue.add(MoveToPointLooking(point, stamina[i]))
            } else {
                queue.add(MoveToPoint(point, stamina[i]))
            }
        }
        Logger.debug("FollowPath queue: $queue")
    }

    private fun getTargetPoint(behaviour: MovementBehaviour): Vector2 {
        return when (behaviour) {
            is MoveToPointLooking -> {
                behaviour.targetPoint
            }
            is MoveToPoint -> {
                behaviour.targetPoint
            }
            else -> {
                Logger.error("Unexpected behaviour class for: $behaviour, class: ${behaviour.javaClass.simpleName}")
                Vector2(0.0, 0.0)
            }
        }
    }

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        // check that we have no more points to visit, and the last point is done being moved to
        return if (queue.isEmpty() && (currentMove?.reportStatus(ctx) != BehaviourStatus.RUNNING)) BehaviourStatus.SUCCESS else status
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        // if currentMove is null, get first point off queue
        if (currentMove == null){
            currentMove = queue.remove()
            Logger.debug("Retrieved first point from queue: ${getTargetPoint(currentMove!!)}")
        }

        // check if we have reached our point
        if (currentMove!!.reportStatus(ctx) != BehaviourStatus.RUNNING && queue.isNotEmpty()){
            currentMove = queue.remove()
            Logger.debug("Reached point, moving to next: ${getTargetPoint(currentMove!!)} (remaining: ${queue.size})")
        }

        // otherwise keep moving to current point
        return currentMove!!.calculateSteering(ctx)
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        return 0.0
    }

    override fun toString(): String {
        return "FollowPath(path=(${path.size} items), stamina=(${stamina.size} items))"
    }


}