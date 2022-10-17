package es.ucm.pdap.sem6.lazy

import kotlin.reflect.KProperty

fun <T> cond(b: Boolean, thenCase: T, elseCase: T) = if (b) thenCase else elseCase

fun <T> cond(b: Boolean, thenCase: () -> T, elseCase: () -> T) = if (b) thenCase() else elseCase()

data class Concepto(val descripcion: String, val cantidad: Int)

fun List<Concepto>.sumaCantidades(): Int = this.map { it.cantidad }.sum()

// Primera versión: atributo inicializado en el constructor
data class Nomina(val devengos: List<Concepto>, val deducciones: List<Concepto>) {
    val total: Int = devengos.sumaCantidades() - deducciones.sumaCantidades()
}

// Segunda versión: método getter
data class Nomina2(val devengos: List<Concepto>, val deducciones: List<Concepto>) {
    val total: Int
        get() = devengos.sumaCantidades() - deducciones.sumaCantidades()
}

// Tercera versión: atributo inicializado perezosamente
data class Nomina3(val devengos: List<Concepto>, val deducciones: List<Concepto>) {
    private var _auxTotal: Int? = null

    val total: Int
        get() {
            if (_auxTotal == null) {
                _auxTotal = devengos.sumaCantidades() - deducciones.sumaCantidades()
            }
            return _auxTotal!!
        }
}

// Cuarta versión: uso de clase Lazy<T>
class Lazy<T>(private val computeValue: () -> T) {
    private var auxValue: T? = null

    val value: T
        get() {
            if (auxValue == null) {
                auxValue = computeValue()
            }
            return auxValue!!
        }

    operator fun getValue(obj: Any?, prop: KProperty<*>): T = value
}


data class Nomina4(val devengos: List<Concepto>, val deducciones: List<Concepto>) {
    val total: Lazy<Int> = Lazy { devengos.sumaCantidades() - deducciones.sumaCantidades() }
}

// Quinta versión: uso de delegados

data class Nomina5(val devengos: List<Concepto>, val deducciones: List<Concepto>) {
    val total: Int by Lazy { devengos.sumaCantidades() - deducciones.sumaCantidades() }
}

fun main() {
    val x by Lazy { 1 / 0 }

    println(cond(5 <= 10, { 0 }, { 1 / 0 }))

    val n = Nomina5(
        listOf(
            Concepto("Salario base", 1750), Concepto("Trienios", 200)
        ), listOf(
            Concepto("Cuota obrera", -100)
        )
    )
    println(n.total)
}

