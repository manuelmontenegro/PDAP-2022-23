package es.ucm.pdap.sem4.validated

import es.ucm.pdap.sem4.either.*

sealed interface Validated<out L, out R>

data class Invalid<out L>(val errors: List<L>) : Validated<L, Nothing>
data class Valid<out R>(val value: R) : Validated<Nothing, R>


fun <L, R> Either<L, R>.toValidated() = when (this) {
    is Left -> Invalid(listOf(value))
    is Right -> Valid(value)
}

fun <L, R1, R2, R> Validated<L, R1>.zip(other: Validated<L, R2>, f: (R1, R2) -> R): Validated<L, R> = when (this) {
    is Invalid -> when (other) {
        is Invalid -> Invalid(this.errors + other.errors)
        is Valid -> this
    }

    is Valid -> when (other) {
        is Invalid -> other
        is Valid -> Valid(f(this.value, other.value))
    }
}

fun <L, R1, R2, R3, R> Validated<L, R1>.zip(
    val2: Validated<L, R2>, val3: Validated<L, R3>, f: (R1, R2, R3) -> R
): Validated<L, R> = this.zip(val2, ::Pair).zip(val3) { (v1, v2), v3 -> f(v1, v2, v3) }


fun <L, R1, R2> Validated<L, R1>.map(f: (R1) -> R2): Validated<L, R2> = when (this) {
    is Invalid -> this
    is Valid -> Valid(f(value))
}


data class Cliente(val nombre: String, val edad: Int, val correo: String)

fun construirCliente(nombreStr: String, edadStr: String, correoStr: String): Validated<ErrorMsg, Cliente> =
    checkAndTrim(nombreStr, "nombre").toValidated().zip(
        checkInt(edadStr, "edad").toValidated(),
        checkMail(correoStr, "correo").toValidated(),
    ) { nombre, edad, correo -> Cliente(nombre, edad, correo) }


fun main() {
    println(construirCliente("", "23", "correcto@ucm.es"))
    println(construirCliente("Javier", "", "correcto@ucm.es"))
    println(construirCliente("Javier", "das", "correcto@ucm.es"))
    println(construirCliente("Javier", "32", "incorrecto@ucm@es"))
    println(construirCliente("Javier", "32", "correcto@ucm.es"))
    println(construirCliente("Javier", "", "incorrecto.ucm.es"))
}
