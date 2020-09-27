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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.omicron2d.ai.world.MarkerManager
import io.github.omicron2d.communication.PlayerAgent
import io.github.omicron2d.communication.messages.IncomingInitMessage
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.communication.messages.SeeMessage
import io.github.omicron2d.utils.GeneralConfig
import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION
import io.github.omicron2d.utils.OMICRON2D_VERSION
import io.github.omicron2d.utils.currentConfig
import org.tinylog.kotlin.Logger
import java.io.FileReader
import java.net.InetAddress

/*
 * Notes:
 * I think the best way to arrange the Main is to have it do what it's supposed to, start a single agent.
 * Then make a file called like TestRunner.kt which runs the agents and tools and everything separately.
 * We would also have to write a shell script to launch the agent using an embedded JVM for at the venue.
 * Maybe use java packager with the jvm for that to reduce build size. We can worry about that if we get to it.
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

        Logger.info("Connecting to ${generalConfig.serverHost}:${generalConfig.playerPort}")
        val initMessage = OutgoingInitMessage(generalConfig.teamName, SERVER_PROTOCOL_VERSION, false)

        val agent = PlayerAgent(InetAddress.getByName(generalConfig.serverHost), generalConfig.playerPort)
        agent.connect(initMessage)
        agent.run()

        // the above method call will block until a timeout or an error
        // so, hopefully the agent will have already disconnected itself by here (for example, in a timeout)
        Logger.info("Omicron2D main finishing")
        println("Goodbye!")
    }
}