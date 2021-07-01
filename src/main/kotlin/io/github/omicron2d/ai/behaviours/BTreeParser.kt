/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import io.github.omicron2d.ai.behaviours.lowlevel.Wait

/**
 * Factory class to generate behaviour trees
 */
object BTreeParser {
    /**
     * Parses a YAML document and generates a behaviour tree from it.
     * @param fileName YAML file name, with extension
     */
    fun parseBehaviourTree(fileName: String): Behaviour {
        return Wait(1000) // TODO
    }
}