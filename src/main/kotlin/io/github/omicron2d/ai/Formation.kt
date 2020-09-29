/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai

import com.google.gson.GsonBuilder
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.nio.file.Paths

/**
 * Loads a formation file created with the Formation Editor (FormationEditor.kt)
 * @param name the name of the file to load in the form of "FILE.formation"
 */
class Formation(private val name: String) {
    private val positions: Array<Vector2>
    private val gson = GsonBuilder().setPrettyPrinting().create()

    init {
        val file = File(name)
        val text = file.readText()
        positions = gson.fromJson(text, Array<Vector2>::class.java)
        Logger.debug("Loaded formation: $file")
//        for (pos in positions){
//            println(pos)
//        }
    }

    /*
     * Note: boilerplate code to make this work:
     *
     * Logger.debug("CONVERTING TO JSON!!!!")
     * val kryo = Kryo().apply {
     *     register(Vector2::class.java)
     *     register(Array<Vector2>::class.java)
     * }
     * val formation = FormationLoader("formations/starting433.formation", kryo)
     * formation.convertToJson()
     * exitProcess(0)
     */
    /**
     * Legacy function used to bootstrap between Kryo and GSON output
     */
    fun convertToJson(name: String){
        val file = File(name)
        file.createNewFile()
        val stream = FileWriter(file)
        val json = gson.toJson(positions)
        stream.write(json)
        stream.close()
        Logger.debug("Converted to JSON successfully")
    }

    /**
     * @return the position, in rcssserver coordinates, for the given agent in the formation
     */
    fun getPosition(agent: Int): Vector2 {
        return positions[agent]
    }
}