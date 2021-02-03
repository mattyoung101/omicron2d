/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.utils

import com.esotericsoftware.yamlbeans.YamlReader
import io.github.omicron2d.ai.agents.PlayerAgent
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.debug.DebugDisplay
import org.tinylog.kotlin.Logger
import java.io.FileReader
import java.net.InetAddress
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

/**
 * This is intended to replace Main.kt so it's easier to launch multiple agents from one Java process.
 */
object AgentLauncher {
    /**
     * Starts and connects an agent to the server. This method blocks until the agent exits.
     * @param isGoalie if the agent should join as a goalie
     * @param teamLaunch if this agent is being launched as part of a team, disables debug UI (and other things)
     */
    fun launchPlayerAgent(isGoalie: Boolean = false, teamLaunch: Boolean = false){
        // load config from YAML files
        val yamlReader = YamlReader(FileReader("config_general.yml"))
        val generalConfig = yamlReader.read(GeneralConfig::class.java)
        CURRENT_CONFIG.set(generalConfig)
        Logger.info("General config parsed successfully")
        Logger.trace(generalConfig)

        // show debug UI
        if (generalConfig.showDebugDisplay && !teamLaunch){
            Logger.debug("Starting debug UI")
            SwingUtilities.invokeLater {
                val app = DebugDisplay()
                app.pack()
                app.setLocationRelativeTo(null)
                app.isVisible = true
                DEBUG_DISPLAY = app
            }
        } else if (teamLaunch){
            // otherwise there would be way too many windows everywhere
            Logger.debug("Running the debug UI is not supported in team launch mode")
        }

        Logger.info("Connecting to ${generalConfig.serverHost}:${generalConfig.playerPort}")
        val initMessage = OutgoingInitMessage(generalConfig.teamName, SERVER_PROTOCOL_VERSION, isGoalie)

        val agent = PlayerAgent(InetAddress.getByName(generalConfig.serverHost), generalConfig.playerPort, isGoalie)
        agent.connect(initMessage)
        agent.run() // blocking

        // the above method call will block until a timeout or an error
        // so, hopefully the agent will have already disconnected itself by here (for example, in a timeout)
        Logger.info("PlayerAgent.run() has finished, terminating")
        println("Goodbye!")
        exitProcess(0)
    }

    fun launchCoachAgent(){}
}