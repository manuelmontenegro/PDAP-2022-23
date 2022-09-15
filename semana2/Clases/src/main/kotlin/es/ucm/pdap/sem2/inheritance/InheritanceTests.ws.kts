import es.ucm.pdap.sem2.inheritance.*

val c1 = Cuadrado(Punto(0, 0), 10)

println(c1 is Cuadrado)
println(c1 is Rectangulo)
println(c1 is Figura)
println(c1 is Any)

fun identificar(f: Figura) = when (f) {
    is Cuadrado -> println("Es un cuadrado")
    is Rectangulo -> println("Es un rectángulo")
    is Circulo -> println("Es círculo")
    else -> println("No sé qué es")
}

fun ejemplo(f: Figura) {
    // Aquí `f` tiene tipo Figura
    // Solamente se puede acceder a f.posicion
    if (f is Circulo) {
        // Dentro de este bloque f tiene tipo `Circulo`.
        println("Es un círculo de radio ${f.radio}")
    }
    // Aquí `f` vuelve a tener tipo Figura
}


