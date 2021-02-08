/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours.highlevel

import io.github.omicron2d.ai.behaviours.MovementBehaviour
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPoint
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import java.util.*

/**
 * Follows a path of points. Internally dispatches to a series of MoveToPoint behaviours.
 * TODO make this use a child MovementManager?
 * @param path list of points to visit
 * @param stamina for each point in the path array, the max amount of stamina to use when navigating to this point
 */
class FollowPath(private val path: Array<Vector2>, private val stamina: DoubleArray) : MovementBehaviour {
    /** queue of MoveToPoint behaviours to execute */
    private val queue: Queue<MoveToPoint> = LinkedList()
    /** current movement behaviour we are executing, or null if none */
    private var currentMove: MoveToPoint? = null
    private var status = BehaviourStatus.RUNNING

    init {
        if (path.size != stamina.size){
            Logger.error("Path array and stamina array size mismatch")
            status = BehaviourStatus.FAILURE
        } else if (path.isEmpty() || stamina.isEmpty()){
            Logger.error("Path and stamina arrays must not be empty")
            status = BehaviourStatus.FAILURE
        }

        for ((i, point) in path.withIndex()){
            queue.add(MoveToPoint(point, stamina[i]))
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
            Logger.debug("Retrieved first point from queue: ${currentMove!!.targetPoint}")
        }

        // check if we have reached our point
        if (currentMove!!.reportStatus(ctx) != BehaviourStatus.RUNNING && queue.isNotEmpty()){
            currentMove = queue.remove()
            Logger.debug("Reached point, moving to next: ${currentMove!!.targetPoint} (remaining: ${queue.size})")
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