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
import io.github.omicron2d.utils.SAY_CHARSET
import io.github.omicron2d.utils.parserAction
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.Var
import org.tinylog.kotlin.Logger

/**
 * Hear message sent from server to client
 */
data class HearMessage(var time: Int = 0, var sender: MessageSender? = null, var direction: Double? = null,
                       var message: String = "") : IncomingServerMessage {

    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): HearMessage {
            val parser = Parboiled.createParser(HearMessageParser::class.java)
            val parseRunner = ReportingParseRunner<HearMessage>(parser.Expression())

            val result = parseRunner.run(input)
            if (result.hasErrors()) {
                val errors = ErrorUtils.printParseErrors(result)
                throw MessageParseException(errors)
            }

            return result.resultValue
        }
    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class HearMessageParser : SoccerParser<HearMessage>() {
        open var deserialised = HearMessage()

        open fun SayChar(): Rule {
            // as defined on page 51 of the manual
            return AnyOf(SAY_CHARSET)
        }

        open fun Time(): Rule {
            val time = Var<String>()
            return Sequence(OneOrMore(Digit()), time.set(match()), ACTION(parserAction {
                deserialised.time = match().toInt()
            }))
        }

        open fun Direction(): Rule {
            return DecimalNumber()
        }

        open fun RefMessage(): Rule {
            // this is for referee message, usually it's announcing changes in play mode BUT NOT ALWAYS!
            // sometimes it announces calls like "yellow_card_l_1" which I don't want to make an enum for, so we keep it
            // as a string
            // Also interesting to note here is that the specs actually LIE! On page 33 of the docs, the grammar indicates
            // that messages sent by the server will always be quoted. This is a complete fabrication, in fact, messages
            // sent by the referee have no quotes at all. The same lie is espoused on the supposedly up-to-date  online docs
            // as well, which are mostly a direct copy and paste from the 2003 manual and aren't even complete.
            val string = Var<String>()
            return Sequence(OneOrMore(EnumChar()), string.set((match())), ACTION(parserAction {
                deserialised.message = string.get()
                // we could try and parse the enum here, and if it fails, just do a string instead, but that might be tricky
                // plus, we can just do the same elsewhere if it's called for
            }))
        }

        open fun StringMessage(): Rule {
            val string = Var<String>()
            return Sequence('"', Sequence(OneOrMore(SayChar()), string.set(match())), '"', ACTION(parserAction {
                deserialised.message = string.get()
            }))
        }

        open fun Sender(): Rule {
            // page 34 of the manual:
            // "Sender is the relative direction to the sender if it is another player, otherwise it is [the enum]."
            val senderDirection = Var<String>()
            val senderName = Var<String>()
            return FirstOf(
                // the sender was another player, so we get the direction to them
                Sequence(Direction(), senderDirection.set(match()), ACTION(parserAction {
                    deserialised.direction = senderDirection.get().toDouble()
                })),
                // the sender was a referee or a coach, so we get their name
                Sequence(FirstOf(senderNames), senderName.set(match()), ACTION(parserAction {
                    deserialised.sender = MessageSender.valueOf(senderName.get().toUpperCase())
                }))
            )
        }

        open fun Expression(): Rule {
            return Sequence("(hear ", Time(), ' ', Sender(), ' ', FirstOf(StringMessage(), RefMessage()),
                MaybeWhiteSpace(), ')', push(deserialised))
        }
    }
}