/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

@file:Suppress("MoveVariableDeclarationIntoWhen")

package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.PlayMode
import org.tinylog.kotlin.Logger

/**
 * A MessageHandler is a class that Agents (those who inherit from SoccerAgent) can use to deserialise messages and
 * call the appropriate handler function with the deserialised message. In other words, it's a message router.
 */
interface MessageHandler {
    /** Deserialises the message passed, and dispatches it to the correct message handler */
    fun dispatchMessage(msg: String)

    /** Called if there is no parser registered for the given message. Default behaviour just logs. */
    fun handleUnknownMessage(msg: String){
        Logger.trace("No parser for message received by player: $msg")
    }

    /** Called if the given message cannot be parsed. Default behaviour is to log. */
    fun handleIllegalMessage(msg: String, ex: MessageParseException){
        Logger.warn("Failed to parse message: $msg")
        Logger.warn(ex)
    }
}

/**
 * Message handler for player agents
 */
interface PlayerMessageHandler : MessageHandler {
    override fun dispatchMessage(msg: String) {
        val name = msg.split(" ").first().replace("(", "")

        try {
            when (name) {
                "init" -> {
                    handleInitMessage(IncomingInitMessage.deserialise(msg))
                }
                "hear" -> {
                    handleHearMessage(HearMessage.deserialise(msg))
                }
                "see" -> {
                    handleSeeMessage(SeeMessage.deserialise(msg))
                }
                "error" -> {
                    handleErrorMessage(ErrorMessage.deserialise(msg))
                }
                "warning" -> {
                    handleWarningMessage(WarningMessage.deserialise(msg))
                }
                else -> {
                    handleUnknownMessage(msg)
                }
            }
        } catch(e: MessageParseException){
            handleIllegalMessage(msg, e)
        }
    }

    fun handleInitMessage(init: IncomingInitMessage)

    fun handleSeeMessage(see: SeeMessage)

    fun handleHearMessage(hear: HearMessage)

    fun handleErrorMessage(error: ErrorMessage)

    fun handleWarningMessage(warning: WarningMessage)
}