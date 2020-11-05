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
import io.github.omicron2d.communication.messages.MoveMessage
import io.github.omicron2d.communication.messages.SeeMessage
import io.github.omicron2d.communication.messages.TurnMessage
import io.github.omicron2d.communication.messages.TurnNeckMessage
import io.github.omicron2d.utils.*
import mikera.vectorz.Vector2
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.tinylog.kotlin.Logger
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.random.Random

/**
 * This class provides a simple method to very roughly check the accuracy of the localisation algorithm. It moves the
 * agent to a random position on our half of the field, then compares that known position to the localiser estimated one.
 * We then plug the distance between those two points into Apache's [DescriptiveStatistics] to figure out how the
 * localiser is performing.
 *
 * TODO make all lastX variables atomic, might fix timing problems.
 */
class LocalisationTesterAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port) {
    /** last known position we randomly moved to, where (0,0) is at the centre of the field */
    private var lastPosition = Vector2(0.0, 0.0)
    /** last known angle we turned to, converted to radians in 0 to 2pi */
    private var lastAngle = 0.0
    private val errorStats = DescriptiveStatistics()
    private val timingStats = DescriptiveStatistics()
    private val angleStats = DescriptiveStatistics()
    private var i = 0

    private fun moveToRandom(){
        val angleRange = 180

        // only allow spawning in our half
        val x = Random.nextDouble(-51.7, -0.75)
        val y = Random.nextDouble(-34.0, 34.0)
        val angle = Random.nextInt(-angleRange, angleRange)

        // move to random position and turn head to random angle
        transmit(arrayOf(MoveMessage(x, y)/*, TurnNeckMessage(angle)*/))
        lastPosition = Vector2(x, y)
        lastAngle = 0.0 //((angle * DEG_RAD) + PI2) % PI2
    }

    override fun run() {
        Logger.debug("LocalisationTesterAgent main loop started")

        while (true){
            val msg = messages.poll(30, TimeUnit.SECONDS)
            if (msg == null){
                Logger.warn("Unexpected null message from message queue, server dead? Terminating!")
                break
            } else if (msg == "INTERNAL_TIMED_OUT"){
                Logger.warn("Received server timeout message, terminating agent!")
                break
            }
            if (i == 0){
                Logger.debug("First run, moving to centre")
                transmit(MoveMessage(-1.0, 0.0))
                i++
                continue
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
                    val begin = System.currentTimeMillis()
                    val agentTransform = ICPLocalisation.performLocalisation(observations)
                    val end = System.currentTimeMillis() - begin

                    // compute error statistics
                    val dst = agentTransform.pos.distance(lastPosition)
                    Logger.debug("($i) Estimate: $agentTransform, dist err: $dst")
                    Logger.debug("($i) Estimated angle: ${agentTransform.theta * RAD_DEG}, real angle: ${lastAngle * RAD_DEG}")
                    errorStats.addValue(dst)
                    timingStats.addValue(end.toDouble())
                    angleStats.addValue(angleDistanceRad(lastAngle, agentTransform.theta))

                    if (i++ % 25 == 0 && i >= 25){
                        Logger.debug("INCOMING STATISTICS DUMP")
                        Logger.debug("Position error stats:\n$errorStats")
                        Logger.debug("Timing stats:\n$timingStats")
                        Logger.debug("Angle error stats:\n$angleStats")
                    }
                } else {
                    Logger.warn("Can't see anything - no flags?")
                }

                moveToRandom()
            }
        }
    }
}