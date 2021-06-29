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
import io.github.omicron2d.utils.SoccerRole
import org.tinylog.kotlin.Logger
import java.io.FileReader

/**
 * A formation currently consists of two YAML files: positions_%name%.yml, and roles_%name%.yml, where %name% is the
 * name of the formation. For example, "433" yields positions_433.yml and roles_433.yml
 *
 * The positions file is an array of each agent's position and is created with the Formation Editor
 * (FormationEditor.kt in the utils package). The roles file contains the SoccerRole enum instance to which each agent
 * will be assigned, and has to be manually created.
 *
 * Note that the last agent (id 10, unum 11) is considered the goalie.
 * @param name name of the formation, WITHOUT extension. For example, for the 433 formation, just use "433".
 */
class Formation(val name: String) {
    private val positions: Array<Vector2>
    private val roles = mutableMapOf<Int, SoccerRole>()

    init {
        val formationsName = "formations/positions_$name.yml"
        Logger.debug("Loading formation from file: $formationsName")
        val reader = YamlReader(FileReader(formationsName))
        positions = reader.read(Array<Vector2>::class.java)
    }

    init {
        val rolesName = "formations/roles_$name.yml"
        Logger.debug("Loading formation roles from file: $rolesName")
        val reader = YamlReader(FileReader(rolesName))
        val rolesStr = reader.read(HashMap::class.java)

        for ((key, value) in rolesStr){
            roles[key.toString().toInt()] = SoccerRole.valueOf(value.toString())
        }
    }

    /**
     * Locates the position for the given agent
     * @param agent the ID of the agent (zero indexed, NOT unum!)
     * @return the position, in rcssserver coordinates, for the given agent in the formation
     */
    fun getPosition(agent: Int): Vector2 {
        return positions[agent]
    }

    /**
     * Returns the role for the given agent
     * @param agent ID of the agent (NOT unum!)
     * @return the role the agent has been assigned in roles_%formation%.yml
     */
    fun getRole(agent: Int): SoccerRole {
        return roles[agent] ?: throw IllegalArgumentException("Agent ID $agent not in roles file")
    }
}