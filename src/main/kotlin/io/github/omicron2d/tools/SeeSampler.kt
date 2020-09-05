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
import io.github.omicron2d.communication.messages.OutgoingInitMessage
import io.github.omicron2d.utils.GeneralConfig
import io.github.omicron2d.utils.OMICRON2D_VERSION
import io.github.omicron2d.utils.SERVER_PROTOCOL_VERSION
import org.tinylog.kotlin.Logger
import java.io.FileReader

/**
 * The SeeSampler randomly moves a team of players around the field, and samples messages from one of them, to generate
 * an enormous corpus of (see) messages to check the parser with.
 */
object SeeSampler {
    private const val OUTPUT = "see_sampler.txt"

    @JvmStatic
    fun main(args: Array<String>){
        println("Omicron2D v$OMICRON2D_VERSION See Sampler")

        // load config from YAML files
        val yamlReader = YamlReader(FileReader("config_general.yml"))
        val generalConfig = yamlReader.read(GeneralConfig::class.java)
        println("General config parsed successfully")
        generalConfig.teamName = "Omicron2D SeeSampler"

        println("Connecting to ${generalConfig.serverHost}:${generalConfig.playerPort}")
        val initMessage = OutgoingInitMessage(generalConfig.teamName,
            SERVER_PROTOCOL_VERSION, false)
    }
}