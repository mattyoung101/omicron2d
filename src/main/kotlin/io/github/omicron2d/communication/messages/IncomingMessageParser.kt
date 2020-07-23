package io.github.omicron2d.communication.messages

import org.parboiled.BaseParser
import org.parboiled.annotations.BuildParseTree

@BuildParseTree
class IncomingMessageParser : BaseParser<IncomingServerMessage>() {
    // TODO parse EVERYTHING here!
    // TODO are we sure we want to do this?
}