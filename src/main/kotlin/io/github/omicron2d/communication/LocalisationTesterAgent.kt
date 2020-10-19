/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication

import io.github.omicron2d.ai.world.FlagObservationPolar
import io.github.omicron2d.ai.world.ICPLocalisation
import io.github.omicron2d.communication.messages.SeeMessage
import io.github.omicron2d.utils.DEFAULT_PLAYER_PORT
import io.github.omicron2d.utils.ObjectType
import mikera.vectorz.Vector2
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.stat.descriptive.SummaryStatistics
import org.tinylog.kotlin.Logger
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class LocalisationTesterAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port) {
    private var lastPosition = Vector2(0.0, 0.0)
    private var lastAngle = 0
    private val stats = DescriptiveStatistics()
    private var i = 0

    private fun moveToRandom(){
        val angleRange = 180

        // only allow spawning in our half
        val x = Random.nextDouble(-51.7, -0.75)
        val y = Random.nextDouble(-34.0, 34.0)
        val angle = Random.nextInt(-angleRange, angleRange)
        val xstr = String.format("%.2f", x)
        val ystr = String.format("%.2f", y)
        Logger.debug("Moving to ($xstr, $ystr), turning to $angle")

        // move to random position and turn head to random angle
        transmitString("(move $xstr $ystr)")
        // TODO add angle transform back in, not testing that yet
        //transmitString("(move $xstr $ystr)(turn_neck $angle)")
        lastPosition = Vector2(x, y)
        lastAngle = angle
    }

    override fun run() {
        Logger.debug("LocalisationTesterAgent main loop started")

        while (true){
            val msg = messages.poll(30, TimeUnit.SECONDS)
            if (msg == null){
                Logger.warn("Unexpected null message from message queue, server dead? Terminating!")
                break
            } else if (msg == "INTERNAL_TIMED_OUT"){
                Logger.warn("Received server timeout message, terminating SamplerAgent!")
                break
            }

            if (msg.startsWith("(error")){
                Logger.warn("Received server error: $msg - terminating!")
                break
            } else if (msg.startsWith("(see")){
                // parse see message
                val see = SeeMessage.deserialise(msg)
                val flags = see.objects.filter { it.type == ObjectType.FLAG && it.name.isNotEmpty() && !it.isBehind }

                if (flags.isNotEmpty()){
                    val observations = hashMapOf<String, FlagObservationPolar>()
                    for (flag in flags) {
                        // convert angle from -180 to 180 (from server) to 0 to 360
                        val direction = (flag.direction.toDouble() + 360.0) % 360.0
                        observations[flag.name] = FlagObservationPolar(flag.distance, direction)
                    }

                    // and now we perform localisation
                    val agentTransform = ICPLocalisation.performLocalisation(observations)
                    val dst = agentTransform.pos.distance(lastPosition)
                    Logger.debug("Estimated position: (${agentTransform.pos.x}, ${agentTransform.pos.y})")
                    Logger.debug("Distance error: $dst")
                    stats.addValue(dst)

                    if (i++ % 50 == 0){
                        Logger.debug(stats.toString())
                    }
                } else {
                    Logger.warn("Can't see anything - no flags?")
                }
                moveToRandom()
            }
        }
    }
}