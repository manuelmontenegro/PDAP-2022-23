package es.ucm.pdap.sem4.option

import java.text.SimpleDateFormat
import java.util.*

sealed interface Option<out T>
data class Some<out T>(val value: T) : Option<T>
object None : Option<Nothing>


fun <T : Any?> T.toOption() = if (this == null) None else Some(this)

fun <T, S> Option<T>.flatMap(f: (T) -> Option<S>): Option<S> = when (this) {
    is Some -> f(value)
    is None -> None
}

fun <T, S> Option<T>.map(f: (T) -> S): Option<S> = flatMap { f(it).toOption() }


fun <T> Option<T>.getOrElse(default: () -> T): T = when (this) {
    is Some -> value
    is None -> default()
}

fun <T> Option<T>.filter(f: (T) -> Boolean) = flatMap { if (f(it)) this else None }


data class Persona(val nombre: String, val direccion: Option<Direccion>, val fechaNacimiento: Option<Date>)
data class Direccion(val calle: String, val numero: Int, val piso: Option<Int>)

fun fechaString(p: Persona): String {
    val df = SimpleDateFormat("dd/MM/yyyy")
    return p.fechaNacimiento.map { df.format(it) }.getOrElse { "No hay fecha" }
}

fun fechaStringOption(p: Persona): Option<String> {
    val df = SimpleDateFormat("dd/MM/yyyy")
    return p.fechaNacimiento.map { df.format(it) }
}


fun obtenerPiso(ps: Map<Int, Persona>, id: Int): Option<Int> {
    val p = ps[id].toOption()
    return if (p is Some) {
        if (p.value.direccion is Some) {
            p.value.direccion.value.piso
        } else None
    } else None
}

fun obtenerPiso2(ps: Map<Int, Persona>, id: Int): Option<Int> =
    ps[id].toOption().flatMap { it.direccion }.flatMap { it.piso }

fun obtenerPisoStr(ps: Map<Int, Persona>, id: Int): String =
    ps[id].toOption().flatMap { it.direccion }.flatMap { it.piso }.map { it.toString() }.getOrElse { "No hay piso" }

fun obtenerNombre(ps: Map<Int, Persona>, id: Int): Option<String> =
    ps[id].toOption().map { it.nombre }.filter { it != "" }


