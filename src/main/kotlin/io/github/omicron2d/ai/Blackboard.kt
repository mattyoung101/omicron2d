/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

import mikera.vectorz.Vector2

/**
 * The Blackboard is used to share information strictly per agent (else that would be illegal) between different
 * subsystems.
 */
class Blackboard {
    /** localised position of agent **/
    val agentPos = Vector2()
    /** localised position of ball **/
    val ballPos = Vector2()
    /** localised positions of all other teammates visible, including self **/
    val teammatePositions = Array(11) { Vector2() }
    /** localised positions of all opponents **/
    val opponentPositions = Array(11) { Vector2() }

    companion object {
        val localBlackboards: ThreadLocal<Blackboard> = ThreadLocal.withInitial { Blackboard() }
    }
}