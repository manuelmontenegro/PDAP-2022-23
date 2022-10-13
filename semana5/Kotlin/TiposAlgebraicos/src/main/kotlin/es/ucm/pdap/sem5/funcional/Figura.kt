package es.ucm.pdap.sem5.funcional

sealed interface Figura

data class Punto(val x: Double, val y: Double)

data class Circulo(val centro: Punto, val radio: Double): Figura
data class Rectangulo(val centro: Punto, val ancho: Double, val alto: Double): Figura
data class Segmento(val desde: Punto, val hasta: Punto): Figura

fun area(f: Figura) = when (f) {
    is Circulo -> f.radio * f.radio * Math.PI
    is Rectangulo -> f.ancho * f.alto
    is Segmento -> 0.0
}