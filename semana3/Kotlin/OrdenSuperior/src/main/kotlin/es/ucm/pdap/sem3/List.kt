package es.ucm.pdap.sem3

import kotlin.collections.List

fun startsWithVowel(s: String) =
    s.isNotEmpty() && s[0].uppercaseChar() in listOf('A', 'E', 'I', 'O', 'U')

fun List<Int>.sum() = fold(0) { ac, x -> ac + x }
fun List<Int>.multiply() = fold(1, Int::times)
fun <T> List<T>.reversed() = fold(listOf()) { ac: List<T>, x: T -> listOf(x) + ac }
fun <T> List<T>.count(elem: T) = fold(0) { ac, x -> if (x == elem) ac + 1 else ac }
fun <T> List<T>.countWith(f: (T) -> Boolean) = fold(0) { ac, x -> if (f(x)) ac + 1 else ac }


fun main() {
    println(listOf(-2, 1, 5, 6).map { it * it })
    println(listOf(1, 6, 9, -20).map { it + 1 })

    println(listOf("Gloria", "Daniel", "Estela", "Juan").map { it.length })

    data class Point(val x: Int, val y: Int)

    println(listOf(Point(1, 4), Point(6, -1), Point(-4, 5)).map { (x, y) -> x })
    println(listOf(Point(1, 4), Point(6, -1), Point(-4, 5)).map { (x, y) -> x + y })

    println(listOf("A", "B", "C").mapIndexed(::Pair))
    println(listOf(1, 6, 4, 3, 5).filter { it % 2 == 0 })

    println(listOf(Point(3, -3), Point(1, 5), Point(0, 3), Point(-1, -1)).filter { (x, y) -> x >= 0 && y >= 0 })

    println(listOf("Manuel", "Oscar", "Aarón", "Irene", "Daniela").filter(::startsWithVowel))

    println(listOf(6, -1, 3, 4).sum())
    println(listOf(6, -1, 3, 4).multiply())
    println(listOf(6, -1, 3, 4).reversed())
    println(listOf(6, -1, 1, 0, 3, 1).count(1))
    println(listOf(6, -1, 1, 0, 3, 1).countWith { it > 0 })

    println(listOf(1, 6, 3) + listOf(1, 4, 2))

    println(listOf(6, -1, 1, 0, 3, 1).max())

    println(listOf(1, 7, 3, 2, 0, 3).scan(0, Int::plus))
    println(listOf("A", "B", "C").scan("", String::plus))

}

