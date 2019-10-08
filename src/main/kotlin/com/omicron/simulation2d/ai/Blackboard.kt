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
    val teamPositions = Array<Vector2>(11) { Vector2() }
}