/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.ai.planning

import org.tinylog.kotlin.Logger
import java.util.*

/**
 * Implements a simple STRIPS-like planning algorithm, see
 * [https://en.wikipedia.org/wiki/Stanford_Research_Institute_Problem_Solver].
 *
 * This code is ported and slightly improved from my GOAP implementation called goaplite, originally written in C:
 * [https://github.com/mattyoung101/goaplite/blob/master/goap.c]
 *
 * Note that we used a breadth-first search as our algorithm, nothing smart like A* because we currently have no
 * costs on our actions (and also BFS is simple to implement).
 */
class STRIPSPlanner : Planner {
    override fun calculatePlan(initialState: ImmutableWorldState, targetState: ImmutableWorldState, actions: List<PlanAction>): Queue<PlanAction> {
        // check if we're already at the goal state for some reason
        if (compareWorldState(initialState, targetState)){
            Logger.warn("Goal state is already satisfied, no planning required!")
            return LinkedList()
        }

        // ok for reference: the linked GOAP implementation uses a stack instead of a queue (hence why it is a
        // depth first search)
        val queue: Queue<Node> = LinkedList()
        val solutions = mutableListOf<Node>()

        // add our initial world state to the queue
        val initial = Node(parents = listOf(), worldState = initialState.toMutableMap())
        queue.add(initial)
        var count = 0

        while (queue.isNotEmpty()){
            println("Queue has ${queue.size} elements")
            val node = queue.remove()
            count++

            // (just debug stuff)
            println("Visiting node with ${node.parents.size} parents")
            if (node.parents.isNotEmpty()){
                println("Parents are:\n${dumpActionList(node.parents)}")
            }
            println("World state of this node is:\n${dumpWorldState(node.worldState)}")

            // let's see what actions we can execute in the current world state of the node
            val neighbours = findExecutableActions(node, actions)
            println("List of actions we can perform from this state:\n${dumpActionList(neighbours)}")

            // iterate through each available action and put a new node on the search list
            for (neighbour in neighbours){
                // create a copy of the map and pretend we executed the action
                val worldClone = node.worldState.toMutableMap()
                executeAction(neighbour, worldClone)
                println("After performing ${neighbour.name}, new world state is:\n${dumpWorldState(worldClone)}")

                // add our current considered neighbour to the node in the queue's parent actions
                // this is for when we add it to the queue later
                val parentsClone = node.parents.toMutableList()
                parentsClone.add(neighbour)

                // make a new node with all our updated data
                val newNode = Node(parents = parentsClone, worldState = worldClone)

                // and finally, decide which list to add our node to
                if (compareWorldState(worldClone, targetState)){
                    println("Reached goal! Adding to solutions list")
                    solutions.add(newNode)
                } else {
                    println("Adding new node with ${newNode.parents.size} to queue")
                    queue.add(newNode)
                }
            }
        }
        println("Search is complete. Visited $count nodes, found ${solutions.size} solutions")

        // check for no solutions
        if (solutions.isEmpty()){
            Logger.error("No solutions found in search!")
            Logger.debug("Initial state: $initialState, target state: $targetState, actions available: $actions")
            return LinkedList()
        }

        // sort solutions by least number of steps and return
        // note the listed exception should never happen since we check if the list is empty above
        val bestSolution = solutions.minByOrNull { it.parents.size } ?: throw IllegalStateException()
        val plan = LinkedList<PlanAction>()
        plan.addAll(bestSolution.parents)
        return plan
    }

    /**
     * Compares two world states and returns true if they're functionally equivalent.
     * This function makes the closed-world assumption, see the comments for details.
     */
    private fun compareWorldState(currentState: ImmutableWorldState, goal: ImmutableWorldState): Boolean {
        for (key in goal.keys){
            val curVal = currentState[key]
            val targetVal = goal[key]

            // interesting to note: STRIPS-like planners, such as this one, make the closed world assumption,
            // which we see on this line.
            // if curVal is null, it means that the key from the goal state was not found in the current world state.
            // we assume this means that the currentState key is false (since it hasn't been reached/recorded yet),
            // and thus the world states are not functionally equivalent.
            // this is in fact not the case - the currentState key is merely unobserved, it could in theory be true
            // (although it's unlikely to be so in a planner and world as simple as ours).
            // see: https://en.wikipedia.org/wiki/Closed-world_assumption for a better explanation
            if (curVal != targetVal){
                return false
            }
        }
        return true
    }

    /** Returns true if the given action can be executed in the current world state */
    private fun canPerformAction(action: PlanAction, world: MutableWorldState): Boolean {
        return compareWorldState(world, action.preConditions)
    }

    /**
     * Updates the specified world state by applying the post conditions of the specified action
     * @return the new world after applying the action (since no pointers in Kotlin)
     */
    private fun executeAction(action: PlanAction, world: MutableWorldState) {
        // let's pretend we executed the action, so apply our post-conditions to the new world
        for ((key, value) in action.postConditions) {
            world[key] = value
        }
    }

    /** checks if the given action list contains an entry with the name of "name" */
    private fun containsName(name: String, actionList: List<PlanAction>): Boolean {
        return actionList.firstOrNull { it.name == name } != null
    }

    /** returns the list of actions that can be executed from this node given it's parents and current state */
    private fun findExecutableActions(node: Node, allActions: List<PlanAction>): List<PlanAction> {
        val neighbours = mutableListOf<PlanAction>()
        for (action in allActions){
            // exclude actions that we cannot execute or that are our parents
            if (canPerformAction(action, node.worldState) && !containsName(action.name, node.parents)){
                neighbours.add(action)
            }
        }
        return neighbours
    }

    /** Used for debugging */
    private fun dumpActionList(list: List<PlanAction>): String {
        if (list.isEmpty()){
            return "\t(empty action list)\n"
        }
        val out = StringBuilder()
        for ((i, entry) in list.withIndex()){
            out.append("\t$i. ${entry.name}\n")
        }
        return out.toString()
    }

    private fun dumpWorldState(state: ImmutableWorldState): String {
        if (state.isEmpty()){
            return "\t(empty world state)\n"
        }
        val out = StringBuilder()
        for ((key, value) in state){
            out.append("\t$key: $value\n")
        }
        return out.toString()
    }

    /**
     * A node used in graph search.
     * @param parents actions who were executed previously before this node
     * @param worldState current world state at this node (sum of all parents world states, basically)
     */
    private data class Node(val parents: List<PlanAction>, val worldState: MutableWorldState)
}