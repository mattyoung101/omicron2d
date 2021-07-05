/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.behaviours

import com.esotericsoftware.yamlbeans.YamlReader
import io.github.omicron2d.ai.behaviours.lowlevel.Wait
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.HostAccess
import org.reflections.Reflections
import org.tinylog.kotlin.Logger
import java.io.FileReader

/**
 * Factory class to generate behaviour trees from YAML documents
 */
object BTreeParser {
    // use a JavaScript engine to evaluate node allocations in the YAML script
    // some info: https://golb.hplar.ch/2020/04/java-javascript-engine.html (script context, old method)
    // https://www.graalvm.org/reference-manual/js/JavaInteroperability/ (polyglot context, new method)
    private val context = Context.newBuilder("js")
        .allowHostAccess(HostAccess.ALL)
        .allowHostClassLookup { true }
        //.allowExperimentalOptions(true)
        //.option("js.nashorn-compat", "true")
        .build()

    init {
        // so. here's the problem. for whatever absolutely insane stupid reason, Nashorn (and hence GraalJS) refuses to
        // import packages that start with io.* in JavaImporter (seriously, WTF??? why????), it says "io is not defined".
        // That implies that some idiot must have _hardcoded_ it to only with with com.*, org.*, etc packages, but that's
        // a rant for another day. So instead we basically have to re-implement this thing by doing terrible terrible
        // reflection hacks: scan the entire io.github.omicron2d.ai.behaviours package, grab all the classes in that,
        // import it into Graal and hope we don't miss anything. luckily the reflections library is really good!
        // ref: https://stackoverflow.com/a/520339/5007892
        val reflections = Reflections("io.github.omicron2d.ai.behaviours")
        val subtypes = reflections.getSubTypesOf(Behaviour::class.java)
        for (type in subtypes){
            Logger.trace("Registering behaviour class: ${type.name}")
            context.eval("js", "let ${type.simpleName} = Java.type(\"${type.name}\")")
        }

        // also register some enums, currently done manually
        context.eval("js", "let SoccerRole = Java.type(\"io.github.omicron2d.utils.SoccerRole\")")
    }

    private fun evalLine(line: String): Behaviour {
        Logger.debug("Evaluating JS: new $line")
        return context.eval("js", "new $line").asHostObject()
    }

    /**
     * Parses a YAML document and generates a behaviour tree from it.
     * @param fileName YAML document file name, with extension
     */
    fun parseBehaviourTree(fileName: String): Behaviour {
        Logger.debug("Loading behaviour tree: $fileName")
        val reader = YamlReader(FileReader(fileName))
        val document = reader.read() as List<Any>

        // traverse through children of each node in focus
        var current = (document[0] as LinkedHashMap<String, Any>).asIterable().first()
        println(current.key)

        return Wait(1000) // TODO
    }
}