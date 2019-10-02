package com.omicron.simulation2d

import com.github.robocup_atan.atan.model.AbstractTeam
import com.github.robocup_atan.atan.model.ControllerCoach
import com.github.robocup_atan.atan.model.ControllerPlayer
import com.omicron.simulation2d.Values.SERVER_IP
import com.omicron.simulation2d.Values.SERVER_PORT
import com.omicron.simulation2d.agents.PlayerAgent

class Omicron2DTeam : AbstractTeam("Omicron2D", SERVER_PORT, SERVER_IP, false) {
    override fun getNewControllerCoach(): ControllerCoach? {
        // currently we don't have a coach
        return null
    }

    override fun getNewControllerPlayer(i: Int): ControllerPlayer {
        // TODO: use goalie agent!!
        return PlayerAgent(i)
    }
}