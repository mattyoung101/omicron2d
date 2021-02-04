/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

/**
 * Class to manage the state of events, for example, if we've kicked off yet or not.
 * @param hasKickedOff set to true if the server has finished kicking off once (only works for PLAY_ON)
 * @param hasReceivedMessage set to true if we have received the first message from the server
 */
data class EventStates(var hasKickedOff: Boolean = false, var hasReceivedMessage: Boolean = false)
