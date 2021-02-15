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
import io.github.omicron2d.ai.behaviours.MovementResult
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPoint
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPointLooking
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import org.tinylog.kotlin.Logger

/**
 * Follows a path of points. Internally dispatches to a series of MoveToPoint behaviours.
 * @param path list of points to visit
 * @param stamina for each point in the path array, the max amount of stamina to use when navigating to this point
 * @param looking if true, uses [MoveToPointLooking] instead of [MoveToPoint]
 */
class FollowPath(private val path: Array<Vector2>, private val stamina: DoubleArray, private val looking: Boolean) : MovementBehaviour {
    private var status = BehaviourStatus.RUNNING
    private var isDone = false
    private var manager = MovementManager {
        Logger.debug("FollowPath MovementManager has finished")
        isDone = true
        null
    }
    private var movementOutput = MovementResult(Vector2(0.0, 0.0), 0.0)

    init {
        if (path.size != stamina.size){
            Logger.error("Path array and stamina array size mismatch")
            status = BehaviourStatus.FAILURE
            isDone = true
        } else if (path.isEmpty() || stamina.isEmpty()){
            Logger.error("Path and stamina arrays must not be empty")
            status = BehaviourStatus.FAILURE
            isDone = true
        }

        for ((i, point) in path.withIndex()){
            if (looking){
                manager.queue.add(MoveToPointLooking(point, stamina[i]))
            } else {
                manager.queue.add(MoveToPoint(point, stamina[i]))
            }
        }
    }

    // update child movement manager
    override fun onUpdate(ctx: AgentContext) {
        movementOutput = manager.tickMovement(ctx)
    }

    override fun reportStatus(ctx: AgentContext): BehaviourStatus {
        // check that we have no more points to visit, and the last point is done being moved to
        return if (isDone) BehaviourStatus.SUCCESS else BehaviourStatus.RUNNING
    }

    override fun calculateSteering(ctx: AgentContext): Vector2 {
        return movementOutput.dash
    }

    override fun calculateTurn(ctx: AgentContext): Double {
        return movementOutput.turn
    }

    override fun toString(): String {
        return "FollowPath(path=(${path.size} items), stamina=(${stamina.size} items))"
    }
}