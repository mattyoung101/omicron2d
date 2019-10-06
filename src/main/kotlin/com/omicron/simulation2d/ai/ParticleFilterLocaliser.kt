package com.omicron.simulation2d.ai

import com.omicron.simulation2d.Values.FIELD_DIAGONAL
import org.apache.commons.rng.simple.RandomSource
import org.tinylog.kotlin.Logger
import kotlin.math.abs
import mikera.vectorz.Vector2


private data class Particle(val position: Vector2 = Vector2.of(0.0, 0.0), var weight: Double = 1.0)
/**
 * A known landmark on the soccer field (referred to as flags in rcssserver).
 * @param position The known real position of the landmark in field coords
 * @param distance The distance of the landmark to the agent
 */
private data class Landmark(val position: Vector2 = Vector2.of(0.0, 0.0), var distance: Double = 1.0)

/**
 * Localises the robot on the field based on a particle filter.
 * Based on: https://www.cs.utexas.edu/~teammco/misc/particle_filter/, which is MIT licensed:
 * Copyright (c) 2013 Richard Teammco
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
class ParticleFilterLocaliser {
    private val rng = RandomSource.create(RandomSource.XOR_SHIFT_1024_S)
    private val particles = Array(NUMBER_PARTICLES){ Particle() }
    private val landmarks = hashMapOf<String, Landmark>()
    /** the known orientation of the agent, angle should be counter-clockwise from the positive X axis **/
    var orientation = 0.0
    // source: https://github.com/rcsoccersim/rcssmonitor/blob/c4ece69c7022c8d4077afbf448010d68e7cf2b45/src/field_painter.cpp#L335
    private val landmarkRealPositions = mapOf<String, Vector2>()

    /** Spawns all the particles around a single point (with some noise) **/
    fun setInitialEstimateLocation(pos: Vector2){
        Logger.trace("Spawning particles at $pos")
        for (particle in particles){
            particle.position.set(pos)
            val noise = Vector2.of(rng.nextDouble() * 5.0, rng.nextDouble() * 5.0)
            particle.position.add(noise)
        }
    }

    /** If the Landmark doesn't already exist, creates it and sets the distance. Otherwise, just sets the distance **/
    fun updateLandmark(name: String, newDistance: Double){
        if (name !in landmarks){
            // TODO need to lookup real positions in some sort of HashMap when instantiating a landmark
            Logger.trace("Instantiating new landmark: $name, dist: $newDistance")
            val mark = Landmark()
            mark.distance = newDistance
            landmarks[name] = mark
        } else {
            landmarks[name]!!.distance = newDistance
        }
    }

    private fun calcWeight(estimatedPos: Vector2, particle: Particle, landmark: Landmark): Double {
        val agentDistToLandmark = estimatedPos.distance(landmark.position)
        val particleDistToLandmark = particle.position.distance(landmark.position)
        val diff = abs(agentDistToLandmark - particleDistToLandmark)
        return (FIELD_DIAGONAL - diff) / FIELD_DIAGONAL;
    }

    /** Updates the particle filter simulation, returning the best estimate of the agent's position **/
    fun updateLocalisation(): Vector2 {
        Logger.trace("Have ${landmarks.size} landmarks: ${landmarks.keys.joinToString(", ")}")

        // TODO this all needs to be in a loop for number of steps

        // 0. approximate position using current particles
        val positionSum = Vector2()
        val posWeightSum = Vector2()
        var weightSum = 0.0
        for (particle in particles) {
            particle.apply {
                positionSum.add(position)
                posWeightSum.add(weight * position.x, weight * position.y)
                weightSum += weight
            }
        }
        // weighted estimate from particles
        val estimatedWeightedPos = posWeightSum.divideCopy(weightSum)

        // 1. if the agent moved, translate the particles by the movement
        // how on earth are we supposed to figure this one out?

        // 2. do a random walk if on random walk step

        // 3. estimate weights of every particle

        // 4. normalise weights

        // 5. resample particles (pick based on probability)

        return Vector2.of(0.0 ,0.0)
    }

    companion object {
        private const val NUMBER_PARTICLES = 512
        private const val RAND_WALK_FREQ = 5 // randomly walk every N ticks
        private const val RANDOM_WALK_DISTANCE = 128
    }
}