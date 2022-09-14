
// Condicionales: if

val edad = 21
if (edad >= 18) {
    println("Eres mayor de edad")
} else {
    println("No eres mayor de edad")
}

val mensaje = if (edad >= 18) "Mayor" else "Menor"
println(mensaje)


val direccion = "Sur"
val pos = 5
val nuevaPos = if (direccion == "Este") {
    println("Voy en dirección este")
    pos + 10
} else {
    println("Voy en dirección oeste")
    pos - 10
}
println(nuevaPos)

val diaActual = "Viernes"

// Condicionales: when

when (diaActual) {
    "Lunes" -> println("Ugh")
    "Viernes" -> println("¡Viva!")
    else -> println("Meh")
}

println(when (diaActual) {
    "Lunes" -> "Ugh"
    "Viernes" -> "¡Viva!"
    else -> "Meh"
})

val color1 = "amarillo"
val color2 = "rojo"
val mezcla = when (setOf(color1, color2)) {
    setOf("amarillo", "azul") -> "verde"
    setOf("rojo", "amarillo") -> "naranja"
    setOf("rojo", "azul") -> "violeta"
    else -> "ni idea"
}
println(mezcla)

val x = 10
val y = 20
println(when {
    x > 0 && y > 0 -> "I"
    x < 0 && y > 0 -> "II"
    x < 0 && y < 0 -> "III"
    x > 0 && y < 0 -> "IV"
    else -> "Ninguno"
})

