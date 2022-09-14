// Funciones básicas

fun maximo(x: Int, y: Int) = if (x >= y) x else y
fun cuadrado(x: Int) = x * x
fun sumaDeCuadrados(x: Int): Int {
    val x2 = cuadrado(x)
    return x2 + x2
}
fun abs(x: Int) = if (x >= 0) x else -x
fun signo(x: Int) = when {
    x > 0 -> 1
    x < 0 -> -1
    else -> 0
}

maximo(5, 3)
cuadrado(4)
sumaDeCuadrados(3)
abs(-4)
signo(-4)

// Funciones genéricas

fun <T> contar(arr: Array<T>, elem: T): Int {
    // Devuelve el número de veces que aparece `elem` en `arr`
    var numVeces = 0
    for (x in arr) {
        if (x == elem) {
            numVeces++
        }
    }
    return numVeces
}

println(contar<Int>(arrayOf(5, 1, 7, 7, 1), 1))
println(contar<Double>(arrayOf(4.3, 1.2, 0.0), 1.0))

println(contar(arrayOf(5, 1, 7, 7, 1), 1))
println(contar(arrayOf(4.3, 1.2, 0.0), 1.0))

// Parámetros opcionales

fun delimitarCadena(s: String, antes: String = "(", despues: String = ")") =
    antes + s + despues

delimitarCadena("Hola")
delimitarCadena("Hola", "[")
delimitarCadena("Hola", "<", ">")
delimitarCadena(s = "Manuel", antes = "(", despues = ")")
delimitarCadena(despues = "]", s = "Manuel")

// Número variable de parámetros

fun imprimirTodos(vararg elems: Any) {
    // Dentro de la función, 'elems' tiene tipo
    // Array<String>
    for (e in elems) {
        println("Imprimiendo $e")
    }
}

imprimirTodos(5, "Juana", 34.2, Pair(2, 3))

// Funciones de extensión

fun String.delimitar(antes: String = "(", despues: String = ")") =
    antes + this + despues

"Gerardo".delimitar("[", "]")
"Adios".delimitar()

// Definiciones locales

fun validarDatos(nombre: String, apellidos: String, direccion: String) {
    fun comprobarNoVacia(cadena: String, parametro: String) {
        if (cadena.isEmpty()) {
            throw IllegalArgumentException("$parametro no válido")
        }
    }
    comprobarNoVacia(nombre, "nombre")
    comprobarNoVacia(apellidos, "apellidos")
    comprobarNoVacia(direccion, "direccion")
}

validarDatos("Ximena", "Rodriguez", "C/ Embajadores")

// Funciones infijas

infix fun Int.suma(otro: Int) = this + otro
println(6 suma 7)
