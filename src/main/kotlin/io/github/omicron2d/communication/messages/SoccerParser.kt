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
import io.github.omicron2d.utils.PlayMode
import org.parboiled.BaseParser
import org.parboiled.Rule

/**
 * Base class for classes who implement parsers for messages from rcssserver should implement, instead of BaseParser
 * Basically, this class provides a bunch of common utility functions like number parsers and related so you don't
 * have to copy and paste them for each file.
 * @param V The type of the message that is being parsed, same as what you'd put for BaseParser<V>
 */
@Suppress("FunctionName")
internal open class SoccerParser<V> : BaseParser<V>() {
    open val senderNames = MessageSender.values().map { it.toString().toLowerCase() }.toTypedArray()
    open val playModeNames = PlayMode.values().map { it.toString().toLowerCase() }.toTypedArray()

    open fun Digit(): Rule {
        return CharRange('0', '9')
    }

    open fun EnumChar(): Rule {
        return FirstOf(Digit(), '_', CharRange('a', 'z'))
    }

    open fun DecimalNumber(): Rule {
        // simple parser for decimal numbers (doubles basically), which are all numbers in the see message
        // also accepts exponents in case they end up in there, should be compatible with Double.parseDouble()
        return Sequence(ZeroOrMore('-'), OneOrMore(Digit()), ZeroOrMore('.'), ZeroOrMore(Digit()),
            ZeroOrMore(AnyOf("Ee+-")), ZeroOrMore(Digit()))
    }

    open fun IntegerNumber(): Rule {
        return Sequence(ZeroOrMore('-'), OneOrMore(Digit()))
    }

    open fun MaybeWhiteSpace(): Rule {
        return ZeroOrMore(AnyOf(" \t"))
    }
}