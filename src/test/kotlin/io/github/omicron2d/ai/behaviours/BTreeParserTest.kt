/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import io.github.omicron2d.ai.world.HighLevelWorldModel
import io.github.omicron2d.utils.AgentContext
import org.junit.Test

class BTreeParserTest {
    // junk context for testing
    private val ctx = AgentContext(HighLevelWorldModel(), 0)

    @Test
    fun testSimpleTree(){
        BTreeParser.parseBehaviourTree("src/test/resources/bhv_test.yml")
    }
}