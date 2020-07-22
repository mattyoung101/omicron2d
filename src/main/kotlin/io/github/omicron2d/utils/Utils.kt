package io.github.omicron2d.utils

import de.tudresden.inf.lat.jsexp.Sexp
import org.parboiled.Context

/**
 * Finds the first atomic S-expression whose token contents matches the specified string
 */
fun Sexp.findFirstAtomicString(str: String): Sexp? {
    return firstOrNull { it.isAtomic && it.toString() == str }
}

fun parserAction(func: () -> Unit): Boolean {
    func()
    return true
}