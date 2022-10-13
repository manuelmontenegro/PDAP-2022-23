package es.ucm.pdap.sem5.match

sealed interface Figura

data class Punto(val x: Double, val y: Double)

data class Circulo(val centro: Punto, val radio: Double): Figura
data class Rectangulo(val centro: Punto, val ancho: Double, val alto: Double): Figura
data class Segmento(val desde: Punto, val hasta: Punto): Figura

fun <R> Figura.match(ifCirculo: (Punto, Double) -> R,
                     ifRectangulo: (Punto, Double, Double) -> R,
                     ifLinea: (Punto, Punto) -> R): R = when (this) {
    is Circulo -> ifCirculo(centro, radio)
    is Rectangulo -> ifRectangulo(centro, ancho, alto)
    is Segmento -> ifLinea(desde, hasta)
}

fun area(f: Figura) = f.match(
    { _, radio -> radio * radio * Math.PI },
    { _, ancho, alto -> ancho * alto },
    { _, _ -> 0.0 }
)

