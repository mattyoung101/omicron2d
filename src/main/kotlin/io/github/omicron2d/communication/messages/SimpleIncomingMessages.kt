package io.github.omicron2d.communication.messages

import io.github.omicron2d.PlayMode
import io.github.omicron2d.Side
import io.github.omicron2d.utils.parserAction
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParseTreeUtils
import org.parboiled.support.Var
import org.tinylog.kotlin.Logger

// Source for all protocol information: https://rcsoccersim.github.io/manual/soccerserver.html#protocols
// Should support around about protocol version 15

// TODO either make constructor that deserialises from string, or have companion object implement interface

/**
 * Server to client init message
 */
data class IncomingInitMessage(var side: Side = Side.LEFT, var unum: Int = 0, var playMode: PlayMode = PlayMode.UNKNOWN) : IncomingServerMessage {
    override fun deserialise(input: String) {
        val parser = Parboiled.createParser(IncomingInitMessageParser::class.java)
        val parseRunner = ReportingParseRunner<IncomingInitMessage>(parser.Expression())

        val result = parseRunner.run(input)
        if (result.hasErrors()){
            Logger.warn(ErrorUtils.printParseErrors(result.parseErrors))
            return
        }

        val message = result.parseTreeRoot.value
        this.side = message.side
        this.unum = message.unum
        this.playMode = message.playMode

        val resultTree = ParseTreeUtils.printNodeTree(result)
        println(resultTree)
    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class IncomingInitMessageParser : BaseParser<IncomingInitMessage>() {
        private val deserialised = IncomingInitMessage()
        open val playModeNames = PlayMode.values().map { it.toString().toLowerCase() }.toTypedArray()

        open fun Digit(): Rule {
            return CharRange('0', '9')
        }

        open fun Unum(): Rule {
            val unum = Var<String>()
            return Sequence(OneOrMore(Digit()), unum.set(match()), ACTION(parserAction {
                deserialised.unum = unum.get().toInt()
            }))
        }

        open fun Side(): Rule {
            val side = Var<Char>()
            return Sequence(FirstOf('l', 'r'), side.set(matchedChar()), ACTION(parserAction {
                deserialised.side = if (side.get() == 'l') Side.LEFT else Side.RIGHT
            }))
        }

        open fun PlayMode(): Rule {
            val playMode = Var<String>()
            return Sequence(FirstOf(playModeNames), playMode.set(match()), ACTION(parserAction {
                deserialised.playMode = PlayMode.valueOf(playMode.get().toUpperCase())
            }))
        }

        // allow imperfectly formatted messages
        open fun MaybeWhiteSpace(): Rule {
            return ZeroOrMore(AnyOf(" \t"))
        }

        open fun Expression(): Rule {
            return Sequence("(init ", Side(), " ", Unum(), " ", PlayMode(), MaybeWhiteSpace(), ")",
                ACTION(parserAction {
                    push(deserialised)
                }))
        }
    }
}

/**
 * Server to client error message
 */
data class IncomingErrorMessage(var message: String = "") : IncomingServerMessage {
    override fun deserialise(input: String) {
        // simple and dumb parser
        message = input.split(" ").last().replace(")", "")
    }
}