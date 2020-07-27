package io.github.omicron2d.communication.messages

import io.github.omicron2d.utils.PlayMode
import io.github.omicron2d.utils.Side
import io.github.omicron2d.communication.MessageParseException
import io.github.omicron2d.utils.parserAction
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.Var
import org.tinylog.kotlin.Logger

/**
 * Server to client init message
 */
data class IncomingInitMessage(var side: Side = Side.LEFT, var unum: Int = 0,
                               var playMode: PlayMode = PlayMode.UNKNOWN) : IncomingServerMessage {
    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): IncomingInitMessage {
            val parser = Parboiled.createParser(IncomingInitMessageParser::class.java)
            val parseRunner = ReportingParseRunner<IncomingInitMessage>(parser.Expression())

            val result = parseRunner.run(input)
            if (result.hasErrors()) {
                val errors = ErrorUtils.printParseErrors(result)
                Logger.warn(errors)
                throw MessageParseException(errors)
            }

            return result.parseTreeRoot.value
        }
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
                })
            )
        }
    }
}