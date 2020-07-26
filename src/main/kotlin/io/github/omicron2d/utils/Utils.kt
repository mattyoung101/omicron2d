package io.github.omicron2d.utils

import org.parboiled.Context

/**
 * Utility function for constructing actions in a Parboiled parser
 */
fun parserAction(func: () -> Unit): Boolean {
    func()
    return true
}