package es.ucm.pdap.sem2

sealed class List<out T>

object Nil : List<Nothing>()
data class Cons<T>(val head: T, val tail: List<T>) : List<T>()

typealias NonEmptyList<T> = Cons<T>

fun seqList(from: Int, to: Int): List<Int> =
    if (from >= to) Nil else Cons(from, seqList(from + 1, to))

fun <T> List<T>.length(): Int = when (this) {
    is Nil -> 0
    is Cons -> 1 + tail.length()
    else -> throw UnsupportedOperationException()
}

fun List<Int>.sum(): Int = when (this) {
    is Nil -> 0
    is Cons -> head + tail.sum()
}

fun List<Int>.sum2(): Int {
    tailrec fun List<Int>.sumRec(ac: Int): Int = when (this) {
        is Nil -> ac
        is Cons -> tail.sumRec(ac + head)
    }
    return this.sumRec(0)
}

operator fun <T> List<T>.plus(other: List<T>): List<T> = when (this) {
    is Nil -> other
    is Cons -> Cons(head, tail + other)
}

fun <T> List<T>.reversed(): List<T> = when (this) {
    is Nil -> Nil
    is Cons -> tail.reversed() + Cons(head, Nil)
}


fun <T> List<T>.reversedLinear(): List<T> {
    tailrec fun List<T>.reverseRec(ac: List<T>): List<T> = when (this) {
        is Nil -> ac
        is Cons -> tail.reverseRec(Cons(head, ac))
    }
    return reverseRec(Nil)
}

tailrec fun <T> NonEmptyList<T>.last(): T = when (tail) {
    is Nil -> head
    is Cons -> tail.last()
}
