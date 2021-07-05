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
import io.github.omicron2d.ai.behaviours.Behaviour
import io.github.omicron2d.ai.behaviours.generic.Sequence
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPoint
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPointLooking
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus

/**
 * Follows a path of points. Internally dispatches to a series of MoveToPoint behaviours.
 * @param path list of points to visit
 * @param stamina for each point in the path array, the max amount of stamina to use when navigating to this point
 * @param looking if true, uses [MoveToPointLooking] instead of [MoveToPoint]
 */
class FollowPath(private val path: Array<Vector2>, private val stamina: DoubleArray, private val looking: Boolean) : Behaviour() {
    // internally for this behaviour we just dispatch it out to a sequence node
    private val sequence = Sequence()

    init {
        // TODO fail instead of throwing exception
        if (path.size != stamina.size){
            throw IllegalArgumentException("Path array and stamina array size mismatch")
        } else if (path.isEmpty() || stamina.isEmpty()){
            throw IllegalArgumentException("Path and stamina arrays must not be empty")
        }

        for ((i, point) in path.withIndex()){
            if (looking){
                sequence.children.add(MoveToPointLooking(point, stamina[i]))
            } else {
                sequence.children.add(MoveToPoint(point, stamina[i]))
            }
        }
    }

    override fun onUpdate(ctx: AgentContext): BehaviourStatus{
        return sequence.onUpdate(ctx)
    }

    override fun toString(): String {
        return "FollowPath(path=(${path.size} items), stamina=(${stamina.size} items))"
    }
}