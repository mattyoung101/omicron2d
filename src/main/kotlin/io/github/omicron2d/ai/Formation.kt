/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import com.google.gson.GsonBuilder
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Paths

/**
 * Loads a formation file created with the Formation Editor (FormationEditor.kt)
 * @param name the name of the file to load, with extension
 */
class Formation(private val name: String) {
    private val positions: Array<Vector2>

    init {
        val reader = YamlReader(FileReader(name))
        positions = reader.read(Array<Vector2>::class.java)
    }

    /**
     * Legacy function used to bootstrap between Kryo and GSON output
     * @param name file name
     */
    fun convertToJson(name: String){
        val file = File(name)
        file.createNewFile()
        val stream = FileWriter(file)
        val json = GsonBuilder().setPrettyPrinting().create().toJson(positions)
        stream.write(json)
        stream.close()
        Logger.debug("Converted to JSON successfully")
    }

    /**
     * Potential future function to bootstrap between JSON and YAML output
     * @param name file name
     */
    fun convertToYaml(name: String){
        val file = File(name)
        file.createNewFile()
        val writer = YamlWriter(FileWriter(file))
        writer.write(positions)
        writer.close()
        Logger.debug("Converted to YAML successfully")
    }

    /**
     * @return the position, in rcssserver coordinates, for the given agent in the formation
     */
    fun getPosition(agent: Int): Vector2 {
        return positions[agent]
    }
}