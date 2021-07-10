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
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.HostAccess
import org.graalvm.polyglot.Value
import org.reflections.Reflections
import org.tinylog.kotlin.Logger
import java.io.FileReader

/**
 * Factory class to generate behaviour trees from YAML documents. Behaviour trees are specified in a YAML format.
 * Each line in the YAML file is actually a short JavaScript script, which is used to instantiate the behaviour classes
 * within Kotlin itself. We use the GraalJS engine (replacement for Nashorn) from Oracle to evaluate JS.
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
        // That implies that some idiot must have _hardcoded_ it to only support com.*, org.*, etc packages, but that's
        // a rant for another day. So instead we basically have to re-implement this thing by doing terrible terrible
        // reflection hacks: scan the entire io.github.omicron2d.ai.behaviours package, grab all the classes in that,
        // import it into Graal and hope we don't miss anything. luckily the reflections library is really good!
        // ref: https://stackoverflow.com/a/520339/5007892
        val begin = System.currentTimeMillis()
        registerPackage("io.github.omicron2d.ai.behaviours", Behaviour::class.java)
        registerPackage("io.github.omicron2d.utils", Enum::class.java)
        Logger.debug("Registering BTree classes took ${System.currentTimeMillis() - begin} ms")
    }

    private fun evalJs(js: String): Value {
        Logger.debug("Evaluating JS: $js")
        return context.eval("js", js)
    }

    /**
     * Registers all the classes that inherit from [clazz] in package [packageName] with the JS interpreter.
     */
    private fun <T> registerPackage(packageName: String, clazz: Class<T>){
        val pkg = Reflections(packageName)
        val subtypes = pkg.getSubTypesOf(clazz)
        for (type in subtypes){
            Logger.trace("Registering class: ${type.name}")
            evalJs("let ${type.simpleName} = Java.type(\"${type.name}\");")
        }
    }

    /**
     * Evaluates a YAML line in the JS interpreter
     */
    private fun evalBehaviour(line: String): Behaviour {
        return evalJs("new $line;").asHostObject()
    }

    /**
     * Recursively visits the children of a node, until it reaches a node with no children. Sort of like a depth
     * first search.
     */
    private fun generateChildren(children: List<*>): List<Behaviour> {
        // instances contains all the top level instances for this node
        // nodes with more children are added as well but not "flat-packed", they are nested in their own class
        val instances = mutableListOf<Behaviour>()
        for (child in children){
            if (child is String){
                // we have the node directly (which means that this node also has no children)
                Logger.debug("Visiting String child $child")
                instances.add(evalBehaviour(child))
            } else if (child is LinkedHashMap<*, *>){
                // we have a linked hashmap, which means the node must have more children
                val nodeName = child.asIterable().first().key.toString()
                val nextChild = evalBehaviour(nodeName)

                val newChildren = child.asIterable().first().value as List<*>
                Logger.debug("Visiting map child $nodeName, going to explore ${newChildren.size} new children")
                nextChild.children.addAll(generateChildren(newChildren))
                instances.add(nextChild)
            }
        }
        return instances
    }

    /**
     * Parses a YAML document and generates a behaviour tree from it.
     * @param fileName YAML document file name, with extension
     */
    fun parseBehaviourTree(fileName: String): Behaviour {
        Logger.debug("Loading behaviour tree: $fileName")
        val reader = YamlReader(FileReader(fileName))
        val document = reader.read() as List<*>

        // grab the root node, always top-level element of the YAML
        // note: we can be sure _this_ element is a LinkedHashMap, but no others!
        val rootMap = (document[0] as LinkedHashMap<*, *>).asIterable().first()
        val rootInstance = evalBehaviour(rootMap.key.toString())
        val rootChildren = generateChildren(rootMap.value as List<*>)

        rootInstance.children.addAll(rootChildren)
        Logger.debug("Tree parsed successfully: $rootInstance")

        return rootInstance
    }
}