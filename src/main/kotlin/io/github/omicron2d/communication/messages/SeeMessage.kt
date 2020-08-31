/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.TEAM_NAME_CHARSET
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParseTreeUtils
import org.tinylog.kotlin.Logger

/**
 * The legend itself, the see message from client to server. By far the most challenging to parse and represent.
 * Reference used is mainly pages 33-37 of the manual, and some collected messages from the server
 */
data class SeeMessage(var time: Int = 0) : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): SeeMessage {
            val parser = Parboiled.createParser(SeeMessageParser::class.java)
            val parseRunner = ReportingParseRunner<SeeMessage>(parser.Expression())

            val result = parseRunner.run(input)
            if (result.hasErrors()) {
                val errors = ErrorUtils.printParseErrors(result)
                Logger.warn(errors)
                throw MessageParseException(errors)
            }

            val resultTree = ParseTreeUtils.printNodeTree(result)
            println(resultTree)

            return result.resultValue
        }
    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class SeeMessageParser : SoccerParser<SeeMessage>(){
        private val deserialised = SeeMessageParser()

        /*
         * Ok, so some notes on this parser because it could be the most complex one so far:
         * - we will treat players and possibly goals as special objects
         * - otherwise, for all the flags and lines and stuff, we don't care what they are as an enum, just their ID
         * so this is super low effort to parse, we just look up an ID like "f t r 20" in a map to get its coordinate
         * since we only use it for localisation anyways
         * - lines are kinda wacky, we will have to figure out a smart way to generate a position for that based
         * on the description, page 37 of the manual
         */

        open fun TeamNameChar(): Rule {
            return AnyOf(TEAM_NAME_CHARSET)
        }

        // reference for all of this: page 36 of the manual, top
        open fun FlagName0(): Rule {
            return String("(f c)")
        }

        open fun FlagName1(): Rule {
            return Sequence("(f ", AnyOf("lcr"), " ", AnyOf("tb"), ")")
        }

        open fun FlagName2(): Rule {
            return Sequence("(f ", AnyOf("pg"), " ", AnyOf("lr"), " ",
                AnyOf("tcb"), ")")
        }

        open fun FlagName3(): Rule {
            return Sequence("(f ", AnyOf("lrtb"), " 0)")
        }

        open fun FlagName4(): Rule {
            return Sequence("(f ", AnyOf("lrtb"), " ", AnyOf("lrtb"), " ",
                IntegerNumber(), ")")
        }

        open fun FlagName(): Rule {
            return FirstOf(FlagName0(), FlagName1(), FlagName2(), FlagName3(), FlagName4())
        }

        open fun GoalName(): Rule {
            return Sequence("(g ", AnyOf("lr"), ")")
        }

        open fun BallName(): Rule {
            return String("(b)")
        }

        open fun LineName(): Rule {
            return Sequence("(l ", AnyOf("lrtb"), ")")
        }

        open fun PlayerName(): Rule {
            return Sequence("(p \"", OneOrMore(TeamNameChar()), "\" ", OneOrMore(IntegerNumber()),
                MaybeWhiteSpace(), Optional("goalie"), ")")
        }

        open fun ObjectName(): Rule {
            // match the first object name we see, for example (g r) gets matched to goal right
            return FirstOf(FlagName(), GoalName(), BallName(), LineName(), PlayerName())
        }

        open fun Distance(): Rule {
            return DecimalNumber()
        }

        open fun Direction(): Rule {
            return IntegerNumber()
        }

        open fun DistChange(): Rule {
            return DecimalNumber()
        }

        open fun DirChange(): Rule {
            return DecimalNumber()
        }

        open fun HeadFaceDir(): Rule {
            return IntegerNumber()
        }

        open fun BodyFaceDir(): Rule {
            return IntegerNumber()
        }

        open fun ObjectContents(): Rule {
            return Sequence(Distance(), ' ', Direction(), ' ', Optional(DistChange()), Optional(' '),
                Optional(DirChange()), Optional(' '), Optional(HeadFaceDir()), Optional(' '),
                Optional(BodyFaceDir()))
        }

        open fun Object(): Rule {
            return Sequence('(', ObjectName(), ' ', ObjectContents(), ')')
        }

        open fun Time(): Rule {
            return IntegerNumber()
        }

        open fun Expression(): Rule {
            return Sequence("(see ", Time(), " ", OneOrMore(Sequence(Object(), MaybeWhiteSpace())),
                MaybeWhiteSpace(), ')')
        }
    }
}