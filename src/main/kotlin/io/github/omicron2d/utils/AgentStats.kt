/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

/**
 * Instance to hold statistics about the agent
 * @param unrecognisedMessages messages we received that had no parser
 * @param goodFlagsRate percentage of good flags in messages
 */
data class AgentStats(
    var successfulLocalisations: Int = 0,
    var failedLocalisations: Int = 0,
    val unrecognisedMessages: MutableSet<String> = mutableSetOf(),
    val goodFlagsRate: DescriptiveStatistics = DescriptiveStatistics()
)