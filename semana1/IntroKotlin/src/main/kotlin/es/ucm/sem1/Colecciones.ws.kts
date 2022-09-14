import kotlin.collections.*

// Listas inmutables
val l = listOf(10, 43, 2, 25)
val lAny: List<Any> = listOf("Pepe", 5.6, Pair(1, 4))

l
l[2]
l.size
l.contains(5)
l.indexOf(25)
l.subList(1, 4)


// Conjuntos inmutables
val s = setOf("Daniel", "Clara", "Francisco", "Clara")
s.isEmpty()
s.contains("Clara")
s.size

// Diccionarios inmutables
val d: Map<String, Int> =
    mapOf("Javier" to 23, "Estela" to 21, "Juan" to 23)

d.size
d["Estela"]
d.containsKey("Susana")
d.keys

// Modificar colecciones inmutables: se devuelve una copia con
// el resultado
val l2 = l + 23
println(l2)
println(l)

val s2 = s - "Francisco"
println(s2)


val d2 = d + ("Ana" to 31)
val d3 = d2 - "Estela"
println(d2)
println(d3)

// Listas mutables

val ml = mutableListOf(10, 54, 32, 55)
ml.add(23)
println(ml)
ml[2] = 13
println(ml)
ml.clear()
println(ml)

// Diccionarios mutables

val md = mutableMapOf(1 to "Lunes", 2 to "Martes")
md.put(3, "Miércoles")
md[7] = "Domingo"
println(md)