fun factorial(n: Int): Int =
    if (n == 0) 1 else n * factorial(n - 1)

println(factorial(5))

fun sumaElems(xs: List<Int>): Int =
    if (xs.isEmpty()) 0 else xs[0] + sumaElems(xs.subList(1, xs.size))


sumaElems((1..100).toList())

fun quitarPares(xs: List<Int>): List<Int> = when {
    // Lista vacía
    xs.isEmpty() -> listOf()
    // Lista cuyo primer elemento es par
    xs[0] % 2 == 0 -> quitarPares(xs.subList(1, xs.size))
    // Lista cuyo primer elemento es impar
    else -> listOf(xs[0]) + quitarPares(xs.subList(1, xs.size))
}


tailrec fun <T> buscarElem(xs: List<T>, x: T): Boolean = when {
    // Lista vacía
    xs.isEmpty() -> false
    // El elemento a buscar está al principio
    xs[0] == x -> true
    // El elemento a buscar no está al principio
    else -> buscarElem(xs.subList(1, xs.size), x)
}

val l1 = (1..10000).toList()
println(buscarElem(l1, -3))

fun factorialTail(n: Int): Int {
    tailrec fun factorialRec(i: Int, ac: Int): Int =
        if (i == 0) ac else factorialRec(i - 1, ac * i)

    return factorialRec(n, 1) // Llamada inicial
}

factorialTail(10)

fun quitarParesTail(xs: List<Int>): List<Int> {
    tailrec fun quitarParesRec(xs: List<Int>, ac: List<Int>): List<Int> = when {
        xs.isEmpty() -> ac
        xs[0] % 2 == 0 -> quitarParesRec(xs.subList(1, xs.size), ac)
        else -> quitarParesRec(xs.subList(1, xs.size), ac + xs[0])
    }

    return quitarParesRec(xs, listOf())
}

quitarParesTail(listOf(4, 3, 2, 6, 7))

fun expIterativo(n: Int): Int {
    var i = n
    var result = 1
    while (i > 0) {
        result = result * 2
        i = i - 1
    }
    return result
}

fun exp(n: Int): Int {
    tailrec fun expRec(i: Int, result: Int): Int = when {
        i > 0 -> expRec(i - 1, result * 2)
        else -> result
    }
    return expRec(n, 1)
}