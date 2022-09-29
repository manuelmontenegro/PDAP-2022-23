package es.ucm.pdap.sem3

fun <T> List<T>.count(f: (T) -> Boolean): Int = when (this) {
    is Nil -> 0
    is Cons -> if (f(head)) 1 + tail.count(f) else tail.count(f)
}

fun isMultipleOf(n: Int): (Int) -> Boolean = { it % n == 0 }

fun String.startsWithA() = this.startsWith("A") || this.startsWith("a")


fun main() {
    val xs = persistentListOf(1, 6, -3, 2, 0, 4, 1, 0, -3)
    println(xs.count { it % 2 == 0 })
    println(xs.count { it > 0 })

    println(xs.count(isMultipleOf(3)))
    println(xs.count(isMultipleOf(4)))
    println(xs.count(isMultipleOf(5)))

    val ys = persistentListOf("Abc", "Cde", "gfs")
    println(ys.count(String::startsWithA))
}