/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d

import io.github.omicron2d.utils.AgentLauncher
import io.github.omicron2d.utils.OMICRON2D_VERSION
import io.github.omicron2d.utils.UselessAgent
import org.tinylog.kotlin.Logger
import kotlin.concurrent.thread

/**
 * Launches the entire Omicron2D team, all 11 agents connect and join the server
 */
object TeamMain {
    private val threads = mutableListOf<Thread>()

    @JvmStatic
    fun main(args: Array<String>){
        System.setProperty("log4j.configuration", "log4j.properties")
        System.setProperty("tinylog.configuration", "tinylog.properties")
        Logger.info("Omicron2D v$OMICRON2D_VERSION: Copyright (c) 2019-2020 Matt Young.")

        // ----- note: only for testing
        UselessAgent.launch()
        Thread.sleep(500)

        // launch the entire team
        for (i in 0 until 11){
            val thread = thread(name="Agent $i"){
                Logger.info("Launching agent $i...")
                // last agent is goalie
                AgentLauncher.launchPlayerAgent(isGoalie = i == 10, teamLaunch = true)
            }
            threads.add(thread)
            Thread.sleep(500)
        }

        // wait on each of our agent threads to complete before exiting
        for ((i, thread) in threads.withIndex()){
            thread.join()
            Logger.info("Agent $i has finished")
        }
    }
}