package io.github.omicron2d

import mikera.vectorz.Vector2

// Contains various values and data classes
// TODO move these all to Constants.kt

@Deprecated("Moved to Constants.kt")
object Values {
    // TODO can't remove until we properly move to apache commons maths
    val FIELD_CENTRE = Vector2.of(FIELD_WIDTH / 2.0, FIELD_HEIGHT / 2.0)!!
}

/** A connection to another agent, transmitted via the say() command **/
data class Connection(val id: Int){
    val sent = mutableListOf<AgentMessage>()
    val received = mutableListOf<AgentMessage>()
}

/** A message that has been sent/received from another agent **/
data class AgentMessage(val type: AgentMessages = AgentMessages.NONE, val content: Any? = null, var remoteId: Int = -1)

enum class AgentMessages {
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