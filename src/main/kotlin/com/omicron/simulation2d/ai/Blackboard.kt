package com.omicron.simulation2d.ai

import mikera.vectorz.Vector2

/**
 * The Blackboard is used to share information strictly per agent (else that would be illegal) between different
 * subsystems.
 */
class Blackboard {
    /** localised position of agent **/
    val agentPos = Vector2()
    /** localised position of ball **/
    val ballPos = Vector2()
    /** localised positions of all other teammates visible, including self **/
    val teammatePositions = Array(11) { Vector2() }
    /** localised positions of all opponents **/
    val opponentPositions = Array(11) { Vector2() }

    companion object {
        val localBlackboards: ThreadLocal<Blackboard> = ThreadLocal.withInitial { Blackboard() }
    }
}