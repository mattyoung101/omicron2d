/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d

/** Time to deserialise messages must be less than this */
const val MESSAGE_DESERIALISATION_TIME = 35
/** Number of iterations to time message deserialisation over */
const val MESSAGE_DESERIALISATION_COUNT = 100