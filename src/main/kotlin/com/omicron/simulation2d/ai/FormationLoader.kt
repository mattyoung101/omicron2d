package com.omicron.simulation2d.ai

import mikera.vectorz.Vector2

/**
 * Loads a formation file created with the Formation Editor (FormationEditor.kt)
 */
class FormationLoader(private val name: String) {
    init {
        // TODO load from disk, will this be JSON or Kryo serialised? thinking Kryo since we already have it
    }

    /**
     * @return the position for the given agent in the formation
     */
    fun getPosition(agent: Int): Vector2 {
        return Vector2.of(0.0, 0.0)
    }
}