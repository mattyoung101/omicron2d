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
import io.github.omicron2d.ai.behaviours.lowlevel.MoveToPointLooking
import io.github.omicron2d.utils.AgentContext
import io.github.omicron2d.utils.BehaviourStatus
import io.github.omicron2d.utils.CURRENT_CONFIG
import org.tinylog.kotlin.Logger

/**
 * Behaviour which goes to the ball on the field. In future this will use a path generation algorithm and follow
 * a path, currently it just goes directly to the ball.
 */
class GoToBall : Behaviour() {
    private var lastBallPos = Vector2()
    private var behaviour = MoveToPointLooking(Vector2(0.0, 0.0), 0.0)
    private var missingThreshold = CURRENT_CONFIG.get().goToBallMissingTicks
    private var updateDist = CURRENT_CONFIG.get().goToBallTolerance

    override fun onUpdate(ctx: AgentContext): BehaviourStatus {
        if (ctx.world.ball.lastSeen == -1){
            Logger.error("Ball has not been observed yet!")
            return BehaviourStatus.FAILURE
        }
//        if (abs(ctx.time - ctx.world.ball.lastSeen) > missingThreshold){
//            Logger.error("Ball not seen in a long time! Will not continue!")
//            return BehaviourStatus.FAILURE
//        }

        // TODO if ball not currently visible, move to its last position with velocity estimation

        val ballPos = ctx.world.ball.pos
        val dist = lastBallPos.dst(ballPos)
        if (dist >= updateDist){
            // reassign behaviour - if this ends up creating object churn, add a changeDestination method to the
            // MoveToPointLooking class
            behaviour = MoveToPointLooking(ballPos, 100.0)
        }

        lastBallPos = ballPos
        return behaviour.onUpdate(ctx)
    }
}