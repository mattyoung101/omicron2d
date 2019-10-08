package com.omicron.simulation2d.ai

import com.omicron.simulation2d.Values.FIELD_DIAGONAL
import org.apache.commons.rng.simple.RandomSource
import org.tinylog.kotlin.Logger
import kotlin.math.abs
import mikera.vectorz.Vector2
import kotlin.math.cos
import kotlin.math.sin


private data class Particle(val position: Vector2 = Vector2.of(0.0, 0.0), var weight: Double = 1.0)
/**
 * A known landmark on the soccer field (referred to as flags in rcssserver).
 * @param position The known real position of the landmark in field coords
 * @param distance The distance of the landmark to the agent
 */
private data class Landmark(val position: Vector2 = Vector2.of(0.0, 0.0), var distance: Double = 1.0)

/**
 * Localises the robot on the field based on a particle filter.
 * Based on: https://www.cs.utexas.edu/~teammco/misc/particle_filter/, which is MIT licensed.
 * This is a essentially ported and slightly rewritten version of that.
 */
class ParticleFilterLocaliser {
    private val rng = RandomSource.create(RandomSource.XOR_SHIFT_1024_S)
    private val particles = Array(NUMBER_PARTICLES){ Particle() }
    private val landmarks = hashMapOf<String, Landmark>()
    /** the known orientation of the agent, angle should be counter-clockwise from the positive X axis **/
    var orientation = 0.0
    // source: object_table.cpp from librcsc by Hidehisa Akiyama (thanks for writing them all out!)
    private val landmarkRealPositions = mapOf<String, Vector2>(
        // we can use the infix function "to" here e.g. "This" to Vector2.of(0, 0)
    )

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

        for (i in 0..NUMBER_STEPS) {
            // 0. approximate position using current particles
            val positionSum = Vector2()
            val posWeightSum = Vector2()
            var weightSum = 0.0
            for (particle in particles) {
                particle.apply {
                    positionSum.add(position)
                    posWeightSum.add(position.multiplyCopy(weight))
                    weightSum += weight
                }
            }
            // weighted average from particles
            val estimatedWeightedPos = posWeightSum.divideCopy(weightSum)

            // 1. if the agent moved, translate the particles by the movement
            // TODO how on earth are we supposed to figure this one out?

            // 2. do a random walk if on random walk step
            if (i % RANDOM_WALK_FREQ == 0){
                Logger.trace("Doing random walk (step: $i)")
                for (particle in particles) {
                    val walk = Vector2(
                        (rng.nextDouble() * (RANDOM_WALK_DISTANCE + 1)) - RANDOM_WALK_DISTANCE / 2,
                        (rng.nextDouble() * (RANDOM_WALK_DISTANCE + 1)) - RANDOM_WALK_DISTANCE / 2
                    )
                    particle.position.add(walk)
                }
            }

            // 3. estimate weights of every particle

            // 4. normalise weights

            // 5. resample particles (pick based on probability)
        }

        return Vector2.of(0.0 ,0.0)
    }

    /**
     * Given the position of the agent in field coords, returns the absolute position given the relative position of
     * an object.
     * @param myPos position of agent in absolute field coords
     * @param objAngle angle towards object relative to agent
     * @param objDistance distance to object relative to agent
     * @return absolute position of object in field coords
     */
    fun localiseObject(myPos: Vector2, objAngle: Double, objDistance: Double): Vector2 {
        val cartesian = Vector2(objDistance * cos(objAngle), objDistance * sin(objAngle))
        cartesian.add(myPos)
        return cartesian
    }

    companion object {
        private const val NUMBER_PARTICLES = 512
        private const val RANDOM_WALK_FREQ = 8 // randomly walk every N ticks
        private const val RANDOM_WALK_DISTANCE = 128
        private const val NUMBER_STEPS = 256
    }
}