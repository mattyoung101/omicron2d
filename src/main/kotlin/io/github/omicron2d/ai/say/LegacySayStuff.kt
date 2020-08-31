/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.say

import io.github.omicron2d.utils.AgentMessages

/** A connection to another agent, transmitted via the say() command **/
data class Connection(val id: Int){
    val sent = mutableListOf<AgentMessage>()
    val received = mutableListOf<AgentMessage>()
}

/** A message that has been sent/received from another agent **/
data class AgentMessage(val type: AgentMessages = AgentMessages.NONE, val content: Any? = null, var remoteId: Int = -1)