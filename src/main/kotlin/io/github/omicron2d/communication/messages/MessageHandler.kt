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

import io.github.omicron2d.utils.AGENT_STATS
import org.tinylog.kotlin.Logger

/**
 * A MessageHandler is a class that Agents (those who inherit from SoccerAgent) can use to deserialise messages and
 * call the appropriate handler function with the deserialised message. In other words, it's a message router.
 * It mainly exists to make the code a little cleaner.
 *
 * This interface includes a set of default implementations (that can be overridden) for really basic stuff like
 * parse errors and unknown messages.
 */
interface MessageHandler {
    /**
     * Deserialises the message passed, and dispatches it to the correct message handler. All agents must call this
     * when receiving a message.
     */
    fun dispatchMessage(msg: String)

    /** Called if there is no parser registered for the given message. Default behaviour just logs. */
    fun handleUnknownMessage(msg: String){
        Logger.trace("No parser for message received by player: $msg")
        AGENT_STATS.get().unrecognisedMessages.add(msg)
    }

    /** Called if the given message cannot be parsed. Default behaviour is to log. */
    fun handleIllegalMessage(msg: String, ex: MessageParseException){
        Logger.warn("Failed to parse message: $msg")
        Logger.warn(ex)
    }

    /** Called when any message is parsed successfully. Default behaviour does nothing. */
    fun handleAnyNonErrorMessage(){}
}

/**
 * Message handler for player agents. Contains stuff like init, hear, see, etc.
 */
interface PlayerMessageHandler : MessageHandler {
    override fun dispatchMessage(msg: String) {
        val name = msg.split(" ").first().replace("(", "")
        var error = false

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
                    error = true
                }
                "warning" -> {
                    handleWarningMessage(WarningMessage.deserialise(msg))
                }
                else -> {
                    handleUnknownMessage(msg)
                }
            }
            if (!error) handleAnyNonErrorMessage()
        } catch(e: MessageParseException){
            handleIllegalMessage(msg, e)
        }
    }

    // The implementing PlayerAgent class is expected to override all these to receive the correct message

    fun handleInitMessage(init: IncomingInitMessage)

    fun handleSeeMessage(see: SeeMessage)

    fun handleHearMessage(hear: HearMessage)

    fun handleErrorMessage(error: ErrorMessage)

    fun handleWarningMessage(warning: WarningMessage)
}