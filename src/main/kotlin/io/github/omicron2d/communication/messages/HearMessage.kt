/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.MessageSender
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

/**
 * Hear message sent from server to client
 */
data class HearMessage(var time: Int = 0, var sender: MessageSender? = null, var direction: Double? = null,
                       var message: String = "") : IncomingServerMessage {

    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): HearMessage {
            // lexer stage
            val lexer = ServerMessageLexer(CharStreams.fromString(input))
            lexer.removeErrorListeners()
            lexer.addErrorListener(MessageErrorListener)
            val tokens = CommonTokenStream(lexer)

            // parser stage
            val parser = ServerMessageParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(MessageErrorListener)
            val tree = parser.hearMessage()

            // walk the parse tree, generate the message
            val parseWalker = ParseTreeWalker()
            val hearListener = HearMessageListener()
            parseWalker.walk(hearListener, tree)

            // and return it back
            return hearListener.message
        }
    }

    private class HearMessageListener : ServerMessageBaseListener() {
        val message = HearMessage()

        override fun enterTime(ctx: ServerMessageParser.TimeContext) {
            message.time = ctx.INTEGER().text.toInt()
        }

        override fun enterSender(ctx: ServerMessageParser.SenderContext) {
            if (ctx.direction() != null){
                // sender was player
                message.direction = ctx.direction().text.toDouble()
            } else {
                // sender was an online coach or something
                message.sender = MessageSender.valueOf(ctx.MESSAGE_SENDER().text.uppercase())
            }
        }

        override fun enterSayMessage(ctx: ServerMessageParser.SayMessageContext) {
            if (ctx.QUOTED_TEXT() != null){
                // we have a quoted message sent by the player
                message.message = ctx.QUOTED_TEXT().text.replace("\"", "")
            } else {
                // we have a ref message
                val whichMessage = ctx.PLAYMODE() ?: ctx.REF_MESSAGE()
                message.message = whichMessage.text
            }
        }
    }
}