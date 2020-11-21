/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d

import io.github.omicron2d.utils.*
import org.tinylog.kotlin.Logger

/**
 * Main class for Omicron2D, launches a single PlayerAgent that connects to the server and plays the game
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>){
        System.setProperty("log4j.configuration", "log4j.properties")
        System.setProperty("tinylog.configuration", "tinylog.properties")
        System.setProperty("kryo.unsafe", "false")

        Logger.info("Omicron2D v$OMICRON2D_VERSION: Copyright (c) 2019-2020 Matt Young.")

        // I have abstracted this out to the AgentLauncher object so that it's easier to launch multiple agents
        // This method will block until the agent exits (due to the game finishing, an error, a timeout, etc).
        AgentLauncher.launchPlayerAgent()
    }
}