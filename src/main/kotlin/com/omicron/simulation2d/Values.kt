package com.omicron.simulation2d

import mikera.vectorz.Vector2

// Contains various values and data classes

object Values {
    /** whether or not debug mode is enabled **/
    const val DEBUG = true
    const val SERVER_PORT = 6000
    const val SERVER_IP = "localhost"
    const val FIELD_WIDTH = 105
    const val FIELD_HEIGHT = 68
    const val FIELD_DIAGONAL = 125.1
    val FIELD_CENTRE = Vector2.of(FIELD_WIDTH / 2.0, FIELD_HEIGHT / 2.0)!!
}

/** A connection to another agent, transmitted via the say() command **/
data class Connection(val id: Int){
    val sent = mutableListOf<Message>()
    val received = mutableListOf<Message>()
}

/** A message that has been sent/received from another agent **/
data class Message(val type: Messages = Messages.NONE, val content: Any? = null, var remoteId: Int = -1){
    
}

enum class Messages {
    NONE,

    /** I am ready to engage in the previous request you sent **/
    ACCEPTED,
    /** I am, at this present moment, not willing to engage in the previous request you sent **/
    DECLINED,
    /** Ask me later, I'm not sure right now **/
    WAIT,

    /** I would like to pass the ball to you **/
    SEND_PASS_REQUEST,
    /** I would like to receive the ball from you **/
    RECEIVE_PASS_REQUEST
}

/**
 * Field positions in the 4-3-3 formation. This will be used by the behaviour and movement planners.
 * The keeper is not included because it's a separate agent. This is only for _player_ positions.
 * Sources:
 * - https://www.liveabout.com/offensive-the-4-3-3-formation-3557643
 * - http://www.soccercoachingpro.com/4-3-3-formation/
 * - https://www.active.com/soccer/articles/4-3-3-team
 * - https://en.wikipedia.org/wiki/Formation_(association_football)#4%E2%80%933%E2%80%933
 **/
enum class PlayerRoles {
    // Goal keeper
    KEEPER, // 0

    // Defence
    CENTRE_BACK_LEFT, // 1
    CENTRE_BACK_RIGHT, // 2
    FULL_BACK_LEFT, // 3
    FULL_BACK_RIGHT, // 4

    // Centre
    CENTRE_MID_LEFT, // 5
    CENTRE_MID_RIGHT, // 6
    CENTRE_MID_CENTRE, // 7

    // Attack
    STRIKER, // 8, aka centre forward
    LEFT_WING, // 9
    RIGHT_WING, // 10
}