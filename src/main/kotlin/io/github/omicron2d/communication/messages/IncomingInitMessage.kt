/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.Side
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.util.*

/**
 * Server to client init message
 */
data class IncomingInitMessage(var side: Side = Side.LEFT, var unum: Int = 0,
                               var playMode: PlayMode = PlayMode.UNKNOWN) : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): IncomingInitMessage {
            // source on how to parse this with ANTLR: https://www.baeldung.com/java-antlr
            // source on custom errors: https://stackoverflow.com/a/26573239/5007892

            // lexer stage
            val lexer = ServerMessageLexer(CharStreams.fromString(input))
            lexer.removeErrorListeners()
            lexer.addErrorListener(MessageErrorListener)
            val tokens = CommonTokenStream(lexer)

            // parser stage
            val parser = ServerMessageParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(MessageErrorListener)
            val tree = parser.initMessage()

            // walk the parse tree, generate the message
            val parseWalker = ParseTreeWalker()
            val initListener = InitMessageListener()
            parseWalker.walk(initListener, tree)

            // and return it back
            return initListener.message
        }
    }

    private class InitMessageListener : ServerMessageBaseListener() {
        val message = IncomingInitMessage()

        override fun enterSide(ctx: ServerMessageParser.SideContext) {
            message.side = if (ctx.text == "l") Side.LEFT else Side.RIGHT
        }

        override fun enterUnum(ctx: ServerMessageParser.UnumContext) {
            message.unum = ctx.INTEGER().text.toInt()
        }

        override fun enterPlaymode(ctx: ServerMessageParser.PlaymodeContext) {
            message.playMode = PlayMode.valueOf(ctx.PLAYMODE().text.uppercase(Locale.getDefault()))
        }
    }
}