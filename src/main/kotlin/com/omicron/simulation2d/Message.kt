package com.omicron.simulation2d

enum class Messages {
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

//data class Message<T: Any>(val id: Messages, val content: T)
data class Message(val id: Messages, val content: Any)