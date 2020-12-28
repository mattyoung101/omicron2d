/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.planning

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

object PlanConfigParser {
    /**
     * Parses an Omicron2D planner configuration file (those which end in *.omplan), using ANTLR.
     * The ANTLR grammar for this is located in PlannerConfig.g4.
     * @param text the config code to be parsed
     */
    fun parsePlan(text: String){
        // lexer
        val lexer = PlannerConfigLexer(CharStreams.fromString(text))
        lexer.removeErrorListeners()
        lexer.addErrorListener(PlanErrorListener)
        val tokens = CommonTokenStream(lexer)

        // parser
        val parser = PlannerConfigParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(PlanErrorListener)
        val tree = parser.document()

        // walk the parse tree
        val parseWalker = ParseTreeWalker()
        val listener = PlanConfigListener()
        parseWalker.walk(listener, tree)
    }

    private class PlanConfigListener : PlannerConfigBaseListener() {

    }
}