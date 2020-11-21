/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.testagents

import io.github.omicron2d.communication.AbstractSoccerAgent
import io.github.omicron2d.utils.DEFAULT_PLAYER_PORT
import org.tinylog.kotlin.Logger
import java.io.PrintStream
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * The SamplerAgent is an agent that connects to the server, and rapidly moves around to random valid positions on the field
 * (as long as the server is in BEFORE_KICK_OFF) and writes the data it sees to disk.
 *
 * TODO allow specifying which message we are looking to record
 */
class SamplerAgent(private val stream: PrintStream,
                   host: InetAddress = InetAddress.getLocalHost(),
                   port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port) {

    private fun moveToRandom(){
        // taken from manual page 31, these values are + or -
        val xRange = 52.5
        val yRange = 34.0
        val angleRange = 180

        val x = Random.nextDouble(-xRange, xRange)
        val y = Random.nextDouble(-yRange, yRange)
        val angle = Random.nextInt(-angleRange, angleRange)
        val xstr = String.format("%.2f", x)
        val ystr = String.format("%.2f", y)
        Logger.debug("Moving to ($xstr, $ystr), turning to $angle")

        // move to random position and turn head to random angle
        transmitString("(move $xstr $ystr)(turn_neck $angle)")
    }

    override fun run() {
        Logger.debug("SamplerAgent main loop started")

        while (true){
            val msg = messages.poll(30, TimeUnit.SECONDS)
            if (msg == null){
                Logger.warn("Unexpected null message from message queue, server dead? Terminating!")
                break
            } else if (msg == "INTERNAL_TIMED_OUT"){
                Logger.warn("Received server timeout message, terminating agent!")
                break
            }

            if (msg.startsWith("(error")){
                Logger.warn("Received server error: $msg - terminating!")
                break
            } else if (msg.startsWith("(see")){
                Logger.debug("Writing message to log")
                stream.println(msg)
                moveToRandom()
            }
        }

        Logger.debug("Flushing output")
        stream.flush()
    }
}