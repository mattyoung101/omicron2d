package com.omicron.simulation2d.ai

import com.omicron.simulation2d.Values.FIELD_HEIGHT
import com.omicron.simulation2d.Values.FIELD_WIDTH
import mikera.randomz.Randomz
import mikera.vectorz.Vector2
import org.apache.commons.rng.UniformRandomProvider
import org.apache.commons.rng.simple.RandomSource
import kotlin.math.floor


private data class Particle(val position: Vector2 = Vector2.of(0.0, 0.0), var weight: Double = 1.0)

/**
 * Localises the robot on the field based on a particle filter.
 * Based on: https://www.cs.utexas.edu/~teammco/misc/particle_filter/ (MIT license)
 * TODO use apache commons rng here
 */
class ParticleFilterLocaliser {
    private val rng = RandomSource.create(RandomSource.XOR_SHIFT_1024_S)
    private val particles = Array(NUMBER_PARTICLES){ Particle() }

    init {
        // TODO setup particle positions
    }

    fun step(){

    }

    companion object {
        private const val NUMBER_PARTICLES = 512
        private const val RAND_WALK_FREQ = 5 // randomly walk every N ticks
        private const val RANDOM_WALK_DISTANCE = 128
    }
}