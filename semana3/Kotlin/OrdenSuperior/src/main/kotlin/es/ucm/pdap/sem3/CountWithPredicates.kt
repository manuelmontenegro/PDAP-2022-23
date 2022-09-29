package es.ucm.pdap.sem3

sealed interface List<out T>

object Nil : List<Nothing>
class Cons<T>(val head: T, val tail: List<T>) : List<T>

operator fun <T> List<T>.plus(other: List<T>): List<T> = when (this) {
    is Nil -> other
    is Cons -> Cons(head, tail + other)
}

fun <T> persistentListOf(vararg elems: T): List<T> {
    tailrec fun persistentListOfRec(i: Int, ac: List<T>): List<T> = when {
        i < 0 -> ac
        else -> persistentListOfRec(i - 1, Cons(elems[i], ac))
    }
    return persistentListOfRec(elems.size - 1, Nil)
}


fun List<Int>.countEven(): Int = when (this) {
    is Nil -> 0
    is Cons -> if (head % 2 == 0) 1 + tail.countEven() else tail.countEven()
}

fun List<Int>.countPositive(): Int = when (this) {
    is Nil -> 0
    is Cons -> if (head > 0) 1 + tail.countPositive() else tail.countPositive()
}


fun List<String>.countNonEmpty(): Int = when (this) {
    is Nil -> 0
    is Cons -> if (head != "") 1 + tail.countNonEmpty() else tail.countNonEmpty()
}

fun interface Predicate<T> {
    fun test(elem: T): Boolean
}

fun <T> List<T>.count(f: Predicate<T>): Int = when (this) {
    is Nil -> 0
    is Cons -> if (f.test(head)) 1 + tail.count(f) else tail.count(f)
}

class IsEven : Predicate<Int> {
    override fun test(elem: Int): Boolean = elem % 2 == 0
}

class IsPositive : Predicate<Int> {
    override fun test(elem: Int): Boolean = elem > 0
}

class IsNonEmpty : Predicate<String> {
    override fun test(elem: String): Boolean = elem != ""
}

class IsMultipleOf(val num: Int) : Predicate<Int> {
    override fun test(elem: Int): Boolean = elem % num == 0
}

fun main() {
    val xs = persistentListOf(1, 6, -3, 2, 0, 4, 1, 0, -3)
    println(xs.count(IsEven()))
    println(xs.count(IsPositive()))
    println(xs.count(IsMultipleOf(2)))
    println(xs.count(IsMultipleOf(4)))
    println(xs.count(IsMultipleOf(7)))

    println(xs.count { it % 2 == 0 })
    println(xs.count { it > 0 })

    val ys = persistentListOf("Foo", "", "Bar", "", "")
    println(ys.count(IsNonEmpty()))

    println(ys.count { it != "" })
}