/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

import com.badlogic.gdx.math.Vector2
import com.esotericsoftware.yamlbeans.YamlReader
import com.esotericsoftware.yamlbeans.YamlWriter
import com.google.gson.GsonBuilder
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileReader
import java.io.FileWriter

/**
 * Loads a formation file created with the Formation Editor (FormationEditor.kt).
 * The positions array is zero indexed, you must use ID (not unum).
 * Note that the last agent (id 10) is considered the goalie.
 * @param name the name of the file to load, with extension
 */
class Formation(val name: String) {
    private val positions: Array<Vector2>

    init {
        Logger.debug("Loading formation: $name")
        val reader = YamlReader(FileReader(name))
        positions = reader.read(Array<Vector2>::class.java)
    }

    /**
     * Legacy function used to bootstrap between Kryo and GSON output
     * @param name file name
     */
    @Deprecated("No longer used, file format is now YAML")
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
     * Writes this formation to disk as a YAML file. Not currently used.
     * @param name file name
     */
    fun writeToYaml(name: String){
        val file = File(name)
        file.createNewFile()
        val writer = YamlWriter(FileWriter(file))
        writer.write(positions)
        writer.close()
        Logger.debug("Converted to YAML successfully")
    }

    /**
     * Locates the position for the given agent
     * @param agent the ID of the agent (zero indexed, NOT unum!)
     * @return the position, in rcssserver coordinates, for the given agent in the formation
     */
    fun getPosition(agent: Int): Vector2 {
        return positions[agent]
    }
}