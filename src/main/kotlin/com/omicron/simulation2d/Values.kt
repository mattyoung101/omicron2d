package com.omicron.simulation2d

import mikera.vectorz.Vector2

object Values {
    /** whether or not debug mode is enabled **/
    const val DEBUG = true
    const val SERVER_PORT = 6000
    const val SERVER_IP = "localhost"
    const val FIELD_WIDTH = 105
    const val FIELD_HEIGHT = 68
    val FIELD_CENTRE = Vector2.of(FIELD_WIDTH / 2.0, FIELD_HEIGHT / 2.0)
}