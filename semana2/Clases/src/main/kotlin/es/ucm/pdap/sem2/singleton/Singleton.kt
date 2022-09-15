package es.ucm.pdap.sem2.singleton

object Biblioteca {
    val libros: List<String> = listOf("Libro1", "Libro2", "Libro3")

    fun numeroLibros(): Int = libros.size
}

fun main() {
    println(Biblioteca.libros)
    println(Biblioteca.numeroLibros())
}