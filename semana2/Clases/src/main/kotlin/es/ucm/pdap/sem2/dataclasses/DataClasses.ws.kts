
data class Punto(val x: Int, val y: Int) { }

data class Rectangulo(
    val posicion: Punto,
    val ancho: Int,
    val alto: Int) { }

fun sumar(p1: Punto, p2: Punto): Punto {
    val (x1, y1) = p1
    val (x2, y2) = p2
    return Punto(x1 + x2, y1 + y2)
}

val p1 = Punto(1, 4)
val p2 = Punto(1, 4)
p1 == p2

sumar(p1, p2)

println(p1)

p1.copy(x = 3)

