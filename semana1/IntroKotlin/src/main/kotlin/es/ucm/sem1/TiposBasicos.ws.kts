// Aunque una variable esté declara con `val`, puede modificarse
// el valor apuntado por ella
val arr1 = arrayOf(1, 4, 5)
arr1[0] = 4

// Tipos numéricos
val x = 4.04        // Tipo Double
val y = 34          // Tipo Int
val z = 2034L       // Tipo Long

// Caracteres
val c1 = '7'
val c2 = '\n'
c1.digitToInt()

// Booleanos
val b1 = true
val b2 = false
b1 && b2
!b1
b1 || b2

// Cadenas de texto
val nombre = "Diana"
val edad = 38
println("Me llamo $nombre y tengo $edad años")
println("Pero el año que viene tendré ${edad + 1} años")
nombre[0]

// Arrays
val a: Array<Int> = arrayOf(1, 6, 3, 5)  // a = [1, 6, 3, 5]
val b = Array(5) { 0 }                   // b = [0, 0, 0, 0, 0]

println(a[0])           // 1
println(a.size)         // 4
println(a.indices)      // 0..3 (de tipo IntRange, utilizado en bucles)
a[2] = 10
println(a[2])

// Pares
val p = Pair(1, 'b')      // p: Pair<Int, Char>
println(p.first)          // Imprime: 1
println(p.second)         // Imprime: b

// Sintaxis alternativa para obtener las componentes de un par
val (x1, x2) = p
println("($x1, $x2)")

// Conversiones
val i = 23
val d = i.toDouble()
val s = i.toString()
val j = "45".toInt()

// Operador == vs ===
val p1 = Pair(4, 5)
val p2 = Pair(4, 5)
val p3 = p1
val p4 = Pair(6, 7)



println(p1 == p2)
println(p1 === p2)
println(p1 === p3)
println(p1 == p4)