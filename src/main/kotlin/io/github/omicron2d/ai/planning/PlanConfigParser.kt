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
     * @return an action list which can be provided to a planner
     */
    fun parseActions(text: String): List<PlanAction> {
        // lexer
        val lexer = PlannerConfigLexer(CharStreams.fromString(text))
        lexer.removeErrorListeners()
        lexer.addErrorListener(PlanParserErrorListener)
        val tokens = CommonTokenStream(lexer)

        // parser
        val parser = PlannerConfigParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(PlanParserErrorListener)
        val tree = parser.document()

        // walk the parse tree
        val parseWalker = ParseTreeWalker()
        val listener = PlanConfigListener()
        parseWalker.walk(listener, tree)

        return listener.actions
    }

    private class PlanConfigListener : PlannerConfigBaseListener() {
        val actions = mutableListOf<PlanAction>()

        // while in the server message parser we use the listener for each event (since they mostly don't depend
        // on each other), I find it's easier to just have a listener for each action here and iterate over the
        // pre/post conditions manually - the DSL is very simple anyway :)
        override fun enterAction(ctx: PlannerConfigParser.ActionContext) {
            val name = ctx.actionName().LITERAL().toString()
            val preConditions = mutableMapOf<String, Boolean>()
            val postConditions = mutableMapOf<String, Boolean>()

            // note: if condition.NOT() is null, there is no exclamation mark before the condition (so it is true!)
            for (condition in ctx.preConditions().conditionList().condition()){
                preConditions[condition.LITERAL().toString()] = condition.NOT() == null
            }
            for (condition in ctx.postConditions().conditionList().condition()){
                postConditions[condition.LITERAL().toString()] = condition.NOT() == null
            }

            actions.add(PlanAction(name, preConditions, postConditions))
        }
    }
}