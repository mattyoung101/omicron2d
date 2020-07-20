package com.omicron.sim2d.agents

import org.tinylog.kotlin.Logger

/**
 * Abstract class for all intelligent agents on the soccer simulation server, for example, players and coaches.
 */
abstract class SoccerAgent {
    var isConnected = false

    /**
     * Connects this agent to the soccer simulation server on the specified IP address and port
     */
    fun connect(address: String, port: Int){

    }

    /**
     * Disconnects from the server, if one is connected to
     */
    fun disconnect(){
        if (!isConnected){
            Logger.warn("Tried to disconnect an agent that is not connected")
            return
        }

        // otherwise, hit the disconnect
    }
}