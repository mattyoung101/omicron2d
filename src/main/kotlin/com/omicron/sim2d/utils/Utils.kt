package com.omicron.sim2d.utils

import de.tudresden.inf.lat.jsexp.Sexp

/**
 * Finds the first atomic S-expression whose token contents matches the specified string
 */
fun Sexp.findFirstAtomicString(str: String): Sexp? {
    return firstOrNull { it.isAtomic && it.toString() == str }
}