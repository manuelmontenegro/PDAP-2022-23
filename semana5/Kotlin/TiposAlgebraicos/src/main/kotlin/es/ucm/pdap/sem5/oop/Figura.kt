package es.ucm.pdap.sem5.oop

sealed interface Figura {
    fun area(): Double
}

data class Punto(val x: Double, val y: Double)

data class Circulo(val centro: Punto, val radio: Double): Figura {
    override fun area() = radio * radio * Math.PI
}
data class Rectangulo(val centro: Punto, val ancho: Double, val alto: Double): Figura {
    override fun area() = ancho * alto
}
data class Segmento(val desde: Punto, val hasta: Punto): Figura {
    override fun area() = 0.0
}

