package com.omicron.simulation2d.ai

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import java.io.FileInputStream
import java.nio.file.Paths

/**
 * Loads a formation file created with the Formation Editor (FormationEditor.kt)
 * @param name the name of the file to load in the form of "FILE.formation"
 */
class FormationLoader(name: String, kryo: Kryo) {
    private val positions: Array<Vector2>

    init {
        val file = Paths.get(name).toAbsolutePath().toFile()
        val input = Input(FileInputStream(file))
        positions = kryo.readObject(input, Array<Vector2>::class.java)
        input.close()
//        Logger.debug("Loaded formation: $file")
    }

    /**
     * @return the position, in rcssserver coordinates, for the given agent in the formation
     */
    fun getPosition(agent: Int): Vector2 {
        return positions[agent]
    }
}