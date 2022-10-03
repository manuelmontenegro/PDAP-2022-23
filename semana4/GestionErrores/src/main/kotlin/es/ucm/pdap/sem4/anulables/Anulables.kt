package es.ucm.pdap.sem4.anulables

import java.util.Date

data class Persona(val nombre: String, val direccion: Direccion?, val fechaNacimiento: Date)
data class Direccion(val calle: String, val numero: Int, val piso: Int?)

fun obtenerPiso(ps: Map<Int, Persona>, id: Int): Int? {
    val p = ps[id]
    return if (p != null) {
        if (p.direccion != null) {
            p.direccion.piso
        } else null
    } else null
}

fun obtenerPiso2(ps: Map<Int, Persona>, id: Int): Int? = ps[id]?.direccion?.piso

fun obtenerPiso3(ps: Map<Int, Persona>, id: Int): String = ps[id]?.direccion?.piso?.toString() ?: "No hay piso"

fun imprimirDireccion(ps: Map<Int, Persona>, id: Int) {
    if (ps.containsKey(id)) {
        println(ps[id]!!.direccion)
    }
}

fun incrementar(x: Int) = x + 1

fun pisoSuperior(d: Direccion): Int? = d.piso?.let(::incrementar)
