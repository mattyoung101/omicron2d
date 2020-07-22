package io.github.omicron2d

import com.github.robocup_atan.atan.model.AbstractTeam
import com.github.robocup_atan.atan.model.ControllerCoach
import com.github.robocup_atan.atan.model.ControllerPlayer
import io.github.omicron2d.Values.SERVER_IP
import io.github.omicron2d.Values.SERVER_PORT
import io.github.omicron2d.agents.PlayerAgent

class Omicron2DTeam : AbstractTeam("Omicron2D", SERVER_PORT, SERVER_IP, false) {
    override fun getNewControllerCoach(): ControllerCoach? {
        // currently we don't have a coach
        return null
    }

    override fun getNewControllerPlayer(i: Int): ControllerPlayer {
        return PlayerAgent(i)
    }
}