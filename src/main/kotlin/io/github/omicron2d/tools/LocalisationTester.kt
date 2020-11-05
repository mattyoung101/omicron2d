/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.tools

import com.esotericsoftware.yamlbeans.YamlReader
import io.github.omicron2d.communication.LocalisationTesterAgent
import io.github.omicron2d.communication.SamplerAgent
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.debug.DebugDisplay
import io.github.omicron2d.utils.GeneralConfig
import io.github.omicron2d.utils.OMICRON2D_VERSION
import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION
import io.github.omicron2d.utils.debugDisplay
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.PrintStream
import java.net.InetAddress
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

/**
 * The LocalisationTester agent moves to a random position on our half of the field and compares the distance from
 * the known random position to the determined localised one
 */
object LocalisationTester {
    @JvmStatic
    fun main(args: Array<String>){
        Logger.info("Omicron2D v$OMICRON2D_VERSION Localisation Tester")

        // load config from YAML files
        val yamlReader = YamlReader(FileReader("config_general.yml"))
        val generalConfig = yamlReader.read(GeneralConfig::class.java)
        generalConfig.teamName = "Omi2DLocaliser"
        Logger.debug("General config parsed successfully")

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

        val agent = LocalisationTesterAgent(InetAddress.getByName(generalConfig.serverHost), generalConfig.playerPort)
        agent.connect(initMessage)
        agent.run()

        Logger.info("LocalisationTester main finishing")
        println("Goodbye!")
        exitProcess(0)
    }
}