package io.github.omicron2d.communication.messages

import io.github.omicron2d.MessageSender
import io.github.omicron2d.SAY_CHARSET
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
 * Hear message sent from server to client
 */
data class HearMessage(var time: Int = 0, var sender: MessageSender? = null, var direction: Double? = null,
                       var message: String = "", var neverTouched: String = "DEFAULT_VALUE") : IncomingServerMessage {

    companion object Deserialiser : IncomingMessageDeserialiser {
        override fun deserialise(input: String): HearMessage {
            val parser = Parboiled.createParser(HearMessageParser::class.java)
            val parseRunner = ReportingParseRunner<HearMessage>(parser.Expression())

            val result = parseRunner.run(input)
            if (result.hasErrors()) {
                val errors = ErrorUtils.printParseErrors(result)
                Logger.warn(errors)
                throw MessageParseException(errors)
            }

            return result.resultValue
        }
    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class HearMessageParser : BaseParser<HearMessage>() {
        private val deserialised = HearMessage()
        open val senderNames = MessageSender.values().map { it.toString().toLowerCase() }.toTypedArray()

        open fun Digit(): Rule {
            return CharRange('0', '9')
        }

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
            // digits are a double, so we try and build a double parser, who knows how well it works
            return Sequence(ZeroOrMore('-'), OneOrMore(Digit()), ZeroOrMore('.'), ZeroOrMore(Digit()),
                // exponent (1.234E+09 or 1.234E-09)
                ZeroOrMore(AnyOf("Ee+-")), ZeroOrMore(Digit()))
        }

        open fun SayString(): Rule {
            val string = Var<String>()
            return Sequence(OneOrMore(SayChar()), string.set(match()), ACTION(parserAction {
                deserialised.message = string.get()
            }))
        }

        open fun Sender(): Rule {
            // page 34 of the manual:
            // "Sender is the relative direction to the sender if it is another player, otherwise it is [the enum]."
            val senderDirection = Var<String>()
            val senderName = Var<String>()
            return FirstOf(
                Sequence(Direction(), senderDirection.set(match()), ACTION(parserAction {
                    deserialised.direction = senderDirection.get().toDouble()
                })),
                Sequence(FirstOf(senderNames), senderName.set(match()), ACTION(parserAction {
                    deserialised.sender = MessageSender.valueOf(senderName.get().toUpperCase())
                }))
            )
        }

        open fun MaybeWhiteSpace(): Rule {
            return ZeroOrMore(AnyOf(" \t"))
        }

        open fun Expression(): Rule {
            return Sequence("(hear ", Time(), " ", Sender(), " \"", SayString(), "\"", MaybeWhiteSpace(), ")",
                ACTION(parserAction {
                    push(deserialised)
                })
            )
        }
    }
}