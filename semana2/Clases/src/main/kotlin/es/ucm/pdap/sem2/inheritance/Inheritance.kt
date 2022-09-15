package es.ucm.pdap.sem2.inheritance

import java.lang.Math.PI

data class Punto(val x: Int, val y: Int)

interface Dibujable {
    fun dibujar()
}


abstract class Figura(val posicion: Punto) {
    abstract fun area(): Double
    abstract fun perimetro(): Double
}


open class Rectangulo(posicion: Punto,
                 val ancho: Int,
                 val alto: Int) : Figura(posicion), Dibujable {

    override fun area() = (ancho * alto).toDouble()

    override fun perimetro() = 2.0 * ancho + 2.0 * alto

    override fun dibujar() =
        println("Pintando rectángulo de $ancho x $alto")
}

class Circulo(
    posicion: Punto,
    val radio: Int) : Figura(posicion), Dibujable {

    override fun area() = PI * radio * radio

    override fun perimetro() = 2 * PI * radio

    override fun dibujar() {
        println("Pintando rectángulo de radio: $radio en $posicion")
    }
}

class Cuadrado(posicion: Punto, lado: Int)
    : Rectangulo(posicion, lado, lado) {

    override fun dibujar() =
        println("Pintando cuadrado de lado $ancho en $posicion")
}
