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
import io.github.omicron2d.ai.testagents.SamplerAgent
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.utils.GeneralConfig
import io.github.omicron2d.utils.OMICRON2D_VERSION
import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.PrintStream
import java.net.InetAddress
import kotlin.system.exitProcess

/**
 * The SeeSampler agent randomly moves around the field and samples messages to generate
 * an enormous corpus of (see) messages to check the parser with.
 */
object SeeSampler {
    private val id = System.currentTimeMillis()
    private val outputPath = "see_sampler/see_sampler_$id.txt"

    @JvmStatic
    fun main(args: Array<String>){
        Logger.info("Omicron2D v$OMICRON2D_VERSION See Sampler")

        // load config from YAML files
        val yamlReader = YamlReader(FileReader("config_general.yml"))
        val generalConfig = yamlReader.read(GeneralConfig::class.java)
        generalConfig.teamName = "Omi2DSeeSampler"
        Logger.debug("General config parsed successfully")

        Logger.info("Connecting to ${generalConfig.serverHost}:${generalConfig.playerPort}")
        val initMessage = OutgoingInitMessage(generalConfig.teamName, SERVER_PROTOCOL_VERSION, false)

        // open log file
        val logFile = File(outputPath)
        logFile.createNewFile()
        val stream = PrintStream(FileOutputStream(logFile, false))
        Logger.debug("Writing to file: $logFile")

        val agent = SamplerAgent(stream, InetAddress.getByName(generalConfig.serverHost), generalConfig.playerPort)
        agent.connect(initMessage)
        agent.run()

        Logger.info("SeeSampler main finishing")
        println("Goodbye!")
        exitProcess(0)
    }
}