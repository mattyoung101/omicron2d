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
import io.github.omicron2d.utils.*
import mikera.vectorz.Vector2
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.tinylog.kotlin.Logger
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * This class provides a simple method to very roughly check the accuracy of the localisation algorithm. It moves the
 * agent to a random position/orientation on our half of the field, then compares that known position/dead reckoned
 * orientation to the localiser estimated one. We then plug the distance between those two points into Apache's
 * [DescriptiveStatistics] to get proper statistical results.
 *
 * Note: orientation testing using descriptive stats, and position to a lesser extent, is bugged due to being
 * out of sync, it's like one message behind. Not sure how to fix. In the mean time, for orientation, just count it
 * yourself since we're currently going in increments of 10 degrees.
 *
 * **RESULTS as of 16 November 2020:**
 * - Position data appears to be accurate to 0.08 units
 * - Orientation appears to be accurate to about 2 degrees.
 * - Timing takes 2ms on average
 */
class LocalisationTesterAgent(host: InetAddress = InetAddress.getLocalHost(), port: Int = DEFAULT_PLAYER_PORT)
    : AbstractSoccerAgent(host, port) {
    /** last known position we randomly moved to, where (0,0) is at the centre of the field */
    private var lastPosition = Vector2(0.0, 0.0)
    /** angle estimated by dead reckoning **in radians** (currently) */
    private var currentAngle = 0.0
    private val positionStats = DescriptiveStatistics()
    private val timingStats = DescriptiveStatistics()
    private val angleStats = DescriptiveStatistics()
    /** count of loops we have done */
    private var i = 0
    /** whether we are testing position or orientation accuracy */
    private val testMode = TestMode.POSITION

    private fun moveToRandom(){
        if (testMode == TestMode.POSITION){
            // only allow spawning in our half (hence the ranges on random)
            val x = Random.nextDouble(-51.7, -0.75)
            val y = Random.nextDouble(-34.0, 34.0)
            lastPosition = Vector2(x, y)
            transmit(MoveMessage(x, y))
        } else {
            // turn fixed amount of degrees per tick
            val degrees = 10.0
            transmit(TurnMessage(degrees.toInt()))
            currentAngle += degrees * DEG_RAD
            currentAngle %= PI2
        }
        // it also appears we can't rotate and teleport at the same time :/
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

            // please note for later that we can get accurate head angle using the sense_body message, head_angle

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
                    if (testMode == TestMode.POSITION) {
                        Logger.debug("($i) Estimate pos: $agentTransform, dist err: $dst")
                        positionStats.addValue(dst)
                    } else {
                        Logger.debug("($i) Estimated angle: ${agentTransform.theta * RAD_DEG}, real angle: ${currentAngle * RAD_DEG}")
                        angleStats.addValue(angleDistanceRad(currentAngle, agentTransform.theta))
                    }
                    timingStats.addValue(end.toDouble())

                    // print stats to log
                    if (i++ % 25 == 0 && i >= 25){
                        Logger.debug("INCOMING STATISTICS DUMP")
                        if (testMode == TestMode.POSITION) {
                            Logger.debug("Position error stats:\n$positionStats")
                        } else {
                            Logger.debug("Angle error stats:\n$angleStats")
                        }
                        Logger.debug("Timing stats:\n$timingStats")
                    }
                } else {
                    Logger.warn("Can't see anything - no flags?")
                }
                moveToRandom()
            }
        }
    }

    private enum class TestMode {
        POSITION, ORIENTATION
    }
}