/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import mikera.vectorz.Vector2

// Shame we can't use inheritance for data classes, oh well

/**
 * Class for players, both us and the opposition
 * @param transform the current transform of this player in field coords, as determined by the localiser
 * @param vel velocity of the player, in (TODO determine units)
 * @param isKnown true only if the absolute position of the player is known (so the agent must have localised correctly)
 * @param lastSeen server tick when the object was last visible
 * @param isSelf true if this PlayerObject represents the current agent
 */
data class PlayerObject(
    var transform: Transform2D = Transform2D(Vector2(0.0, 0.0), 0.0),
    var vel: Vector2 = Vector2(0.0, 0.0),
    var isKnown: Boolean = false,
    var unum: Int = -1,
    var lastSeen: Int = -1,
    var isGoalie: Boolean = false,
    var isSelf: Boolean = false)

/**
 * Class for the ball - the only other moving object other than the players (I think at least).
 * @param pos position of the object, with (0,0) being the centre of the field
 * @param vel velocity of the player, in (TODO determine unit)
 * @param isKnown true if the absolute position of the ball is known (so the agent must have localised correctly)
 * @param lastSeen server tick when the object was last visible
 */
data class BallObject(
    var pos: Vector2 = Vector2(0.0, 0.0),
    var vel: Vector2 = Vector2(0.0, 0.0),
    var isKnown: Boolean = false,
    var lastSeen: Int = -1)

/**
 * Contains an observation of a soccer object in polar coordinates
 * @param distance the distance to the object
 * @param angle must be converted to 0 to 360 (NOT -180 to 180 as is sent by server!!)
 */
data class ObjectObservationPolar(val distance: Double, val angle: Double)