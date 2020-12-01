/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.ObjectType
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import kotlin.math.roundToInt

/**
 * Information about player name transmitted through see message
 */
data class SeePlayerInfo(var teamName: String? = null, var unum: Int? = null, var goalie: Boolean = false)

/**
 * A singular object reported by the see message
 * @param type what type of object the flag is
 * @param name the name of the flag, for example `f r t` (TODO does it include brackets?)
 * @param playerInfo if this is a player, some information about the player, otherwise null
 * @param isBehind if this flag is a behind flag, which contains minimal useful info
 */
data class SeeObject(var type: ObjectType = ObjectType.UNKNOWN, var name: String = "",
                     var playerInfo: SeePlayerInfo? = null, var distance: Double? = 0.0, var direction: Int? = 0,
                     var distChange: Double? = null, var dirChange: Double? = null, var headFaceDir: Int? = null,
                     var bodyFaceDir: Int? = null, var isBehind: Boolean = false)

/**
 * The legend itself, the see message from client to server. By far the most challenging to parse and represent.
 * Reference used is mainly pages 33-37 of the manual, and some collected messages from the server
 */
data class SeeMessage(var time: Int = 0, var objects: MutableList<SeeObject> = mutableListOf()) : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): SeeMessage {
            // lexer stage
            val lexer = ServerMessageLexer(CharStreams.fromString(input))
            lexer.removeErrorListeners()
            lexer.addErrorListener(ErrorListener)
            val tokens = CommonTokenStream(lexer)

            // parser stage
            val parser = ServerMessageParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(ErrorListener)
            val tree = parser.seeMessage()

            // walk the parse tree, generate the message
            val parseWalker = ParseTreeWalker()
            val seeListener = SeeMessageListener()
            parseWalker.walk(seeListener, tree)

            // and return it back
            return seeListener.message
        }
    }

    private class SeeMessageListener : ServerMessageBaseListener() {
        val message = SeeMessage()
        // current object we are working with
        var obj = SeeObject()

        override fun enterTime(ctx: ServerMessageParser.TimeContext) {
            message.time = ctx.INTEGER().text.toInt()
        }

        override fun enterSeeObject(ctx: ServerMessageParser.SeeObjectContext) {
            obj = SeeObject()
        }

        override fun exitSeeObject(ctx: ServerMessageParser.SeeObjectContext) {
            message.objects.add(obj)
        }

        override fun enterPlayerName(ctx: ServerMessageParser.PlayerNameContext) {
            val playerName = ctx.teamName()?.QUOTED_TEXT()?.text?.replace("\"", "")
            val playerUnum = ctx.unum()?.INTEGER()?.text?.toIntOrNull()
            val isGoalie = ctx.goalie() != null
            obj.playerInfo = SeePlayerInfo(playerName, playerUnum, isGoalie)
            obj.type = ObjectType.PLAYER
        }

        override fun enterPlayerBehind(ctx: ServerMessageParser.PlayerBehindContext) {
            obj.type = ObjectType.PLAYER
            obj.isBehind = true
        }

        override fun enterFlagName(ctx: ServerMessageParser.FlagNameContext) {
            obj.type = ObjectType.FLAG
            obj.name = ctx.FLAG_NAME().text
        }

        override fun enterFlagBehind(ctx: ServerMessageParser.FlagBehindContext) {
            obj.type = ObjectType.FLAG
            obj.isBehind = true
        }

        override fun enterGoalBehind(ctx: ServerMessageParser.GoalBehindContext) {
            obj.type = ObjectType.GOAL
            obj.isBehind = true
        }

        override fun enterGoalName(ctx: ServerMessageParser.GoalNameContext) {
            obj.type = ObjectType.GOAL
            obj.name = ctx.GOAL_NAME().text
        }

        override fun enterBallName(ctx: ServerMessageParser.BallNameContext) {
            obj.type = ObjectType.BALL
        }

        override fun enterBallBehind(ctx: ServerMessageParser.BallBehindContext) {
            obj.type = ObjectType.BALL
            obj.isBehind = true
        }

        override fun enterLineName(ctx: ServerMessageParser.LineNameContext) {
            obj.type = ObjectType.LINE
            obj.name = ctx.LINE_NAME().text
        }

        override fun enterDistance(ctx: ServerMessageParser.DistanceContext) {
            obj.distance = ctx.text.toDouble()
        }

        override fun enterDirection(ctx: ServerMessageParser.DirectionContext) {
            // we treat it as a double first (since all ints are doubles too), then cast back to an int
            obj.direction = ctx.text.toDouble().roundToInt()
        }

        override fun enterDistChange(ctx: ServerMessageParser.DistChangeContext) {
            obj.distChange = ctx.text.toDouble()
        }

        override fun enterDirChange(ctx: ServerMessageParser.DirChangeContext) {
            obj.dirChange = ctx.text.toDouble()
        }

        override fun enterHeadFaceDir(ctx: ServerMessageParser.HeadFaceDirContext) {
            obj.headFaceDir = ctx.text.toDouble().roundToInt()
        }

        override fun enterBodyFaceDir(ctx: ServerMessageParser.BodyFaceDirContext) {
            obj.bodyFaceDir = ctx.text.toDouble().roundToInt()
        }
    }
}