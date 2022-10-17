package es.ucm.pdap.sem6.sequences

fun from(n: Int) = generateSequence(n) { it + 1 }

fun fromTo(n: Int, m: Int): Sequence<Int> = from(n).take(m - n + 1)

fun ones(): Sequence<Int> = sequence {
    yield(1)
    yieldAll(ones())
}

fun fibs(): Sequence<Int> = sequence {
    yield(0)
    yield(1)
    yieldAll(fibs().zip(fibs().drop(1), Int::plus))
}

fun criba(s: Sequence<Int>): Sequence<Int> = sequence {
    val head = s.first()
    yield(head)
    yieldAll(criba(s.drop(1).filter { it % head != 0 }))
}

fun primos(): Sequence<Int> = criba(from(2))

fun multiplicaPorTres(x: Int): Int {
    println("Multiplicando $x por tres")
    return x * 3
}

fun esPar(x: Int): Boolean {
    println("Comprobando si $x es par")
    return x % 2 == 0
}

fun sumaUno(x: Int): Int {
    println("Incrementando $x")
    return x + 1
}

fun factorial(n: Int) = fromTo(1, n).fold(1) { ac, x -> ac * x }

fun fib(n: Int) =
    generateSequence(Pair(0, 1)) { (x, y) -> Pair(y, x + y) }
        .map { it.first }
        .elementAt(n)

fun power(n: Int, m: Int) =
    generateSequence(1) { it * n }
        .elementAt(m)

fun <T> append(seq1: Sequence<T>, seq2: Sequence<T>): Sequence<T> = sequence {
    yieldAll(seq1)
    yieldAll(seq2)
}

data class Terna(val x: Int, val y: Int, val z: Int)

fun ternasPitagoricas(max: Int): Sequence<Terna> =
    fromTo(1, max).flatMap { x ->
        fromTo(x, max).flatMap { y ->
            fromTo(y, max).map { z -> Terna(x, y, z) }
        }
    }.filter { (x, y, z) -> x * x + y * y == z * z }


fun main() {
    val xs = listOf(2, 3, 8, 11, 34).asSequence()
    val ys = xs.map(::multiplicaPorTres)
        .filter(::esPar)
        .map(::sumaUno)
    val zs = ys.take(2);
    println(zs.toList())

    println(criba(from(2)).take(100).toList())
    println(ternasPitagoricas(25).toList())
}
