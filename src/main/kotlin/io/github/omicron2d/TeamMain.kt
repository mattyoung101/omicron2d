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
import org.tinylog.kotlin.Logger
import kotlin.concurrent.thread

/**
 * Class to run the entire team, instead of just a single agent
 */
object TeamMain {
    private val threads = mutableListOf<Thread>()

    @JvmStatic
    fun main(args: Array<String>){
        System.setProperty("log4j.configuration", "log4j.properties")
        System.setProperty("tinylog.configuration", "tinylog.properties")
        System.setProperty("kryo.unsafe", "false")

        Logger.info("Omicron2D v$OMICRON2D_VERSION: Copyright (c) 2019-2020 Matt Young.")

        // launch the entire team
        for (i in 0 until 11){
            val thread = thread(name="Agent $i"){
                Logger.info("Launching agent $i...")
                AgentLauncher.launchPlayerAgent(isGoalie = i == 10, teamLaunch = true)
            }
            threads.add(thread)
            Thread.sleep(500)
        }

        // wait on all the threads instead of quitting instantly
        for ((i, thread) in threads.withIndex()){
            thread.join()
            Logger.info("Agent $i has finished")
        }
    }
}