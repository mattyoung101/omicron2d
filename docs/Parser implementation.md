# Parser implementation
This document contains information about how the server message parser is implemented in Omicron2D.

## Implementation language
The parser was originally implemented using the Parboiled PEG parser combinator, however, that quickly became too difficult
to use and maintain due to its insistence on doing some very complex bytecode rewriting (perhaps for good reason, unsure),
and lack of helpful error messages when debugging grammars.

The parser is now currently written in ANTLR 4.8, see `src/main/antlr/ServerMessage.g4` for the source. In the package
`io.github.omicron2d.communication.messages`, each message is implemented as a Kotlin data class with a companion object
`Deserialiser` that uses an ANTLR tree walker to construct the message. It (should) also handle syntax errors correctly
by throwing a `MessageParseException` and logging the offending message.

## Unit testing
The parser is checked by a variety of unit tests to make sure it works properly. One such test involves using the SeeSampler
to record a large corpus of the specific message that requires testing, and running it through the parser to make sure it
doesn't cause a syntax error. This helps significantly in detecting strange edge cases that the server emits.

For an example of a corpus recorded by this process and used in testing, see `src/test/resources/SEE_TEST_DATASET.txt`

## Performance
The unit tests also randomly generates (or pulls from a corpus) a large amount of messages (512 currently) to test the
average performance in milliseconds for each message's parser. Currently, our tolerance is a maximum of 35 ms (so that
unit tests don't fail on slow systems), however realistically parsers run in 30 microseconds for simple messages to
2 milliseconds for the most complex ones, which is great.

## Accuracy
In saying that though, the parser will erroneously successfully parse technically invalid messages that could be sent by 
the server in a few cases:

- Due to ANTLR shenanigans, the lexer for the team name is the same as the lexer for a quoted say message. This means
the parser will accept some technically illegal team names.
- The parser will accept possibly illegal whitespace placement, because it ignores whitespace

This means that the parser should not be used as a ground truth, 100% accurate solution, just a mostly-correct solution
that works for Omicron2D.

## Conclusion
The Omicron2D server message parser is written as an ANTLR grammar in `src/main/antlr/ServerMessage.g4`. The parser
is tested, so it should accept all valid messages. However, occasionally it may accept invalid messages as well. It is very
fast.