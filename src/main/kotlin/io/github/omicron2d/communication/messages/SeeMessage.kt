package io.github.omicron2d.communication.messages

import io.github.omicron2d.communication.MessageParseException
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParseTreeUtils
import org.tinylog.kotlin.Logger

/**
 * See message sent by the server. This one's the real doozy to parse...
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
    private open class SeeMessageParser : BaseParser<SeeMessage>(){
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

        open fun Digit(): Rule {
            return CharRange('0', '9')
        }

        open fun Number(): Rule {
            // simple parser for decimal numbers (doubles basically), which are all numbers in the see message
            // also accepts exponents in case they end up in there, should be compatible with Double.parseDouble()
            return Sequence(ZeroOrMore('-'), OneOrMore(Digit()), ZeroOrMore('.'), ZeroOrMore(Digit()),
                ZeroOrMore(AnyOf("Ee+-")), ZeroOrMore(Digit()))
        }

        open fun MaybeWhiteSpace(): Rule {
            return ZeroOrMore(AnyOf(" \t"))
        }

        open fun ObjectName(): Rule {
            return Sequence('(', ')')
        }

        open fun Object(): Rule {
            return Sequence('(', ')')
        }

        open fun Expression(): Rule {
            return Sequence("(see ", OneOrMore(Sequence(Object(), MaybeWhiteSpace())), MaybeWhiteSpace(), ')')
        }
    }
}