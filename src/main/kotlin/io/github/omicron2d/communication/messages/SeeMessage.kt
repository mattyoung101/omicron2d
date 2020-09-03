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
import io.github.omicron2d.utils.TEAM_NAME_CHARSET
import io.github.omicron2d.utils.parserAction
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParseTreeUtils
import org.parboiled.support.Var
import org.tinylog.kotlin.Logger

/**
 * Information about player name transmitted through see message
 */
data class SeePlayerInfo(val teamName: String, val unum: Int, val goalie: Boolean)

/** A singular object reported by the see message */
data class SeeObject(var type: ObjectType = ObjectType.UNKNOWN, var name: String = "",
                     var playerInfo: SeePlayerInfo? = null, var distance: Double = 0.0, var direction: Int = 0,
                     var distChange: Double? = null, var dirChange: Double? = null, var headFaceDir: Int? = null,
                     var bodyFaceDir: Int? = null)

/**
 * The legend itself, the see message from client to server. By far the most challenging to parse and represent.
 * Reference used is mainly pages 33-37 of the manual, and some collected messages from the server
 */
data class SeeMessage(var time: Int = 0, var objects: MutableList<SeeObject> = mutableListOf()) : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): SeeMessage {
            val parser = Parboiled.createParser(SeeMessageParser::class.java)
            val parseRunner = ReportingParseRunner<SeeObject>(parser.Expression())

            val result = parseRunner.run(input)
            if (result.hasErrors()) {
                val errors = ErrorUtils.printParseErrors(result)
                throw MessageParseException(errors)
            }

//            val resultTree = ParseTreeUtils.printNodeTree(result)
//            println(resultTree)
//            println("\n\n\n")
//            println("Value stack has ${result.valueStack.size()} entries")

            val out = SeeMessage()
            // remove parentheses, it would be better to rewrite the parser to ignore brackets in the name matcher
            // but this doesn't seem to work
            for (entry in result.valueStack){
                entry.name = entry.name.replace("(", "").replace(")", "")
            }
            out.objects.addAll(result.valueStack)
            return out
        }
    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class SeeMessageParser : SoccerParser<SeeObject>(){
        /*
         * PARSER NOTES:
         * - for all the flags and lines and stuff, we don't care what they are as an enum, just their ID
         * so this is super low effort to parse, we just look up an ID like "f t r 20" in a map to get its coordinate
         * since we only use it for localisation anyways
         * - lines are kinda wacky, we will have to figure out a smart way to generate a position for that based
         * on the description, page 37 of the manual
         * - we're going to actually have to use the action stack, push instances of SeeObject() onto it
         */

        /*************************************
         * Basic types and variable matchers *
         *************************************/
        open fun TeamNameChar(): Rule {
            return AnyOf(TEAM_NAME_CHARSET)
        }

        open fun PlayerName(): Rule {
            // TODO write parser
            return Sequence("(p \"", OneOrMore(TeamNameChar()), "\" ", OneOrMore(IntegerNumber()),
                MaybeWhiteSpace(), Optional("goalie"), ")")
        }

        open fun Distance(): Rule {
            val dist = Var<String>()
            return Sequence(DecimalNumber(), dist.set(match()), ACTION(parserAction {
                peek().distance = dist.get().toDouble()
            }))
        }

        open fun Direction(): Rule {
            val dir = Var<String>()
            return Sequence(IntegerNumber(), dir.set(match()), ACTION(parserAction {
                peek().direction = dir.get().toInt()
            }))
        }

        open fun DistChange(): Rule {
            val distChange = Var<String>()
            return Sequence(DecimalNumber(), distChange.set(match()), ACTION(parserAction {
                peek().distChange = distChange.get().toDouble()
            }))
        }

        open fun DirChange(): Rule {
            val dirChange = Var<String>()
            return Sequence(DecimalNumber(), dirChange.set(match()), ACTION(parserAction {
                peek().dirChange = dirChange.get().toDouble()
            }))
        }

        open fun HeadFaceDir(): Rule {
            val headFaceDir = Var<String>()
            return Sequence(IntegerNumber(), headFaceDir.set(match()), ACTION(parserAction {
                peek().headFaceDir = headFaceDir.get().toInt()
            }))
        }

        open fun BodyFaceDir(): Rule {
            val bodyFaceDir = Var<String>()
            return Sequence(IntegerNumber(), bodyFaceDir.set(match()), ACTION(parserAction {
                peek().bodyFaceDir = bodyFaceDir.get().toInt()
            }))
        }

        open fun Time(): Rule {
            // FIXME: parsing this is a PITA because we don't actually have our SeeObject() on the value stack yet
            return IntegerNumber()
        }

        /**********************************
         * FLAG AND FIELD OBJECT MATCHERS *
         *********************************/
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
            return Sequence(FirstOf(FlagName0(), FlagName1(), FlagName2(), FlagName3(), FlagName4()), ACTION(parserAction {
                peek().type = ObjectType.FLAG
            }))
        }

        open fun GoalName(): Rule {
            return Sequence("(g ", AnyOf("lr"), ")", ACTION(parserAction {
                peek().type = ObjectType.GOAL
            }))
        }

        open fun BallName(): Rule {
            return Sequence(String("(b)"), ACTION(parserAction {
                peek().type = ObjectType.BALL
            }))
        }

        open fun LineName(): Rule {
            return Sequence("(l ", AnyOf("lrtb"), ")", ACTION(parserAction {
                peek().type = ObjectType.LINE
            }))
        }

        // TODO add the objects that are like (F), not sure what they are

        /*************************
         * OBJECT ENTRY MATCHER *
         ************************/
        open fun ObjectName(): Rule {
            val name = Var<String>()
            return Sequence(FirstOf(FlagName(), GoalName(), BallName(), LineName(), PlayerName()), name.set(match()),
                ACTION(parserAction {
                    peek().name = name.get()
                }))
        }

        open fun ObjectContents(): Rule {
            return Sequence(Distance(), ' ', Direction(), Optional(" ", DistChange()),
                Optional(" ", DirChange()), Optional(" ", HeadFaceDir()), Optional(" ", BodyFaceDir()))
        }

        open fun Object(): Rule {
            // matches an entire object with name and contents
            return Sequence(push(SeeObject()), '(', ObjectName(), ' ', ObjectContents(), ')')
        }

        /********************
         * FINAL EXPRESSION *
         ********************/
        open fun Expression(): Rule {
            return Sequence("(see ", Time(), " ", OneOrMore(Sequence(Object(), MaybeWhiteSpace())),
                MaybeWhiteSpace(), ")")
        }
    }
}