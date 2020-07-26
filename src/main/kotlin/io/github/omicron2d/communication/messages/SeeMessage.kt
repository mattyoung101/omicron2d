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
 * See message sent by the server. The real big one to parse...
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

        open fun Digit(): Rule {
            return CharRange('0', '9')
        }

        open fun Expression(): Rule {
            return ANY // TODO
        }
    }
}