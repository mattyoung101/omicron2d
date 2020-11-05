/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d

import com.esotericsoftware.yamlbeans.YamlReader
import io.github.omicron2d.communication.PlayerAgent
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.debug.DebugDisplay
import io.github.omicron2d.utils.*
import javafx.application.Platform
import javafx.stage.Stage
import org.tinylog.kotlin.Logger
import java.io.FileReader
import java.net.InetAddress
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

/*
 * Notes:
 * I think the best way to arrange the Main is to have it do what it's supposed to, start a single agent.
 * Then make a file called like TestRunner.kt which runs the agents and tools and everything separately.
 * We would also have to write a shell script to launch the agent using an embedded JVM for at the venue.
 * Maybe use java packager with the jvm for that to reduce build size. We can worry about that if we get to it.
 */

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

        // load config from YAML files
        val yamlReader = YamlReader(FileReader("config_general.yml"))
        val generalConfig = yamlReader.read(GeneralConfig::class.java)
        currentConfig = generalConfig
        Logger.info("General config parsed successfully")
        Logger.trace(generalConfig)

        // show debug UI
        if (generalConfig.showDebugDisplay){
            Logger.debug("Starting debug UI")
            SwingUtilities.invokeLater {
                val app = DebugDisplay()
                app.pack()
                app.setLocationRelativeTo(null)
                app.isVisible = true
                debugDisplay = app
            }
        }

        Logger.info("Connecting to ${generalConfig.serverHost}:${generalConfig.playerPort}")
        val initMessage = OutgoingInitMessage(generalConfig.teamName, SERVER_PROTOCOL_VERSION, false)

        val agent = PlayerAgent(InetAddress.getByName(generalConfig.serverHost), generalConfig.playerPort)
        agent.connect(initMessage)
        agent.run() // blocking

        // the above method call will block until a timeout or an error
        // so, hopefully the agent will have already disconnected itself by here (for example, in a timeout)
        Logger.info("PlayerAgent.run() has finished, terminating")
        println("Goodbye!")
        exitProcess(0)
    }
}