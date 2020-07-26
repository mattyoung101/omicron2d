package io.github.omicron2d.communication.messages

import io.github.omicron2d.MessageSender
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParseTreeUtils
import org.tinylog.kotlin.Logger

data class IncomingHearMessage(var time: Int = 0, var sender: MessageSender = MessageSender.UNKNOWN, var direction: Int = 0,
                               var message: String = "") : IncomingServerMessage {
    override fun deserialise(input: String) {
        val parser = Parboiled.createParser(IncomingHearMessageParser::class.java)
        val parseRunner = ReportingParseRunner<IncomingHearMessage>(parser.Expression())

        val result = parseRunner.run(input)
        if (result.hasErrors()){
            Logger.warn(ErrorUtils.printParseErrors(result.parseErrors))
            return
        }

        val resultTree = ParseTreeUtils.printNodeTree(result)
        println(resultTree)
    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class IncomingHearMessageParser : BaseParser<IncomingHearMessage>() {
        open val senderNames = MessageSender.values().map { it.toString().toLowerCase() }.toTypedArray()

        open fun Digit(): Rule {
            return CharRange('0', '9')
        }

        open fun SayChar(): Rule {
            // as defined on page 51 of the manual
            return AnyOf("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM().+*/?<>_ ")
        }

        open fun Time(): Rule {
            return OneOrMore(Digit())
        }

        open fun Direction(): Rule {
            // digits can also be negative
            return Sequence(ZeroOrMore('-'), OneOrMore(Digit()))
        }

        open fun SayString(): Rule {
            return OneOrMore(SayChar())
        }

        open fun Sender(): Rule {
            // page 34 of the manual:
            // "Sender is the relative direction to the sender if it is another player, otherwise it is [the enum]."
            return FirstOf(Direction(), FirstOf(senderNames))
        }

        open fun MaybeWhiteSpace(): Rule {
            return ZeroOrMore(AnyOf(" \t"))
        }

        open fun Expression(): Rule {
            return Sequence("(hear ", Time(), " ", Sender(), " \"", SayString(), "\"", MaybeWhiteSpace(), ")")
        }
    }
}