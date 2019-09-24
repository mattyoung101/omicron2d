package com.omicron.simulation2d

import com.github.robocup_atan.atan.model.ControllerPlayer

abstract class SimplifiedControllerPlayer : ControllerPlayer {
    // TODO in this class, wrap up a bunch of similar functions in one
    // for example instead of having like 6 different hear functions, just have one hear() which specifies also the sender
    // but then what about the type of the message - we will have to make extensive use of generics, is this possible?
}