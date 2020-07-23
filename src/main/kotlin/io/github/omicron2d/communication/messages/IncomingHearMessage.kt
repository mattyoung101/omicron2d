package io.github.omicron2d.communication.messages

import io.github.omicron2d.MessageSender
import org.parboiled.BaseParser
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree

data class IncomingHearMessage(var time: Int = 0, var sender: MessageSender = MessageSender.UNKNOWN, var direction: Int = 0,
                               var message: String = "") : IncomingServerMessage {
    override fun deserialise(input: String) {

    }

    @Suppress("FunctionName")
    @BuildParseTree
    private open class IncomingHearMessageParser : BaseParser<IncomingHearMessage>() {
        open fun Digit(): Rule {
            return CharRange('0', '9')
        }

        open fun Time(): Rule {
            return OneOrMore(Digit())
        }

        open fun Direction(): Rule {
            // digits can also be negative
            return Sequence(ZeroOrMore('-'), OneOrMore(Digit()))
        }

        open fun WhiteSpace(): Rule {
            return ZeroOrMore(AnyOf(" \t"))
        }

        open fun Expression(): Rule {
            // TODO finish parser
            return Sequence("(hear ", Time(), WhiteSpace(), ")")
        }

        // TODO need sender and direction
    }
}