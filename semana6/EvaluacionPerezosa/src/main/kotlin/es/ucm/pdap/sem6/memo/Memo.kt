package es.ucm.pdap.sem6.memo

fun divisoresDe(n: Int): List<Int> = (1..n / 2).filter { n % it == 0 } + n

fun <T, S> memoize(f: (T) -> S): (T) -> S {
    val cache = mutableMapOf<T, S>()
    return { t ->
        if (!cache.containsKey(t)) {
            // println("Calculando función para parámetro $t")
            cache[t] = f(t)
        }
        cache[t]!!
    }
}

fun fib(n: Int): Int = when (n) {
    0 -> 0
    1 -> 1
    else -> fibMemo(n - 1) + fibMemo(n - 2)
}

val fibMemo = memoize(::fib)

fun main() {
    val divisoresDeMemo = memoize(::divisoresDe)

    println(divisoresDeMemo(343))
    println(divisoresDeMemo(23))
    println(divisoresDeMemo(30))
    println(divisoresDeMemo(30))

    println(fibMemo(45))
}
