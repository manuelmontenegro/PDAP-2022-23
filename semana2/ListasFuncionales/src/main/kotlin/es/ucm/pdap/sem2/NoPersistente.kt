package es.ucm.pdap.sem2

import kotlin.collections.List

fun seq(from: Int, to: Int): List<Int> =
    if (from >= to) emptyList()
    else listOf(from) + seq(from + 1, to)


fun main() {
    println(seq(1, 5))
}

