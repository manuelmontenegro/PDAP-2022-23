package es.ucm.pdap.sem4.either

sealed interface Either<out L, out R>

data class Left<out L>(val value: L) : Either<L, Nothing>
data class Right<out R>(val value: R) : Either<Nothing, R>

fun <L, R> conditionally(b: Boolean, ifTrue: () -> R, ifFalse: () -> L) = if (b) Right(ifTrue()) else Left(ifFalse())


fun <T> catch(f: () -> T): Either<Exception, T> = try {
    Right(f())
} catch (e: Exception) {
    Left(e)
}

fun <L, R1, R2> Either<L, R1>.flatMap(f: (R1) -> Either<L, R2>): Either<L, R2> = when (this) {
    is Left -> this
    is Right -> f(value)
}

fun <L, R1, R2> Either<L, R1>.map(f: (R1) -> R2): Either<L, R2> = flatMap { Right(f(it)) }

fun <L, R, S> Either<L, R>.mapLeft(f: (L) -> S): Either<S, R> = when (this) {
    is Left -> Left(f(value))
    is Right -> this
}

val emailRegex =
    ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+").toRegex()


typealias ErrorMsg = String

fun checkAndTrim(str: String, fieldName: String): Either<ErrorMsg, String> =
    conditionally(str.trim() != "", { str.trim() }, { "Empty string: $fieldName" })


fun checkInt(str: String, fieldName: String): Either<ErrorMsg, Int> = checkAndTrim(str, fieldName).flatMap { s ->
    catch { s.toInt() }.mapLeft { "Not a number: $fieldName" }
}


fun checkMail(str: String, fieldName: String): Either<ErrorMsg, String> = checkAndTrim(str, fieldName).flatMap { s ->
    conditionally(emailRegex.matches(s), { s }, { "Invalid email: $fieldName" })
}


data class Cliente(val nombre: String, val edad: Int, val correo: String)

fun construirCliente(nombreStr: String, edadStr: String, correoStr: String): Either<String, Cliente> =
    checkAndTrim(nombreStr, "nombre").flatMap { nombre ->
        checkInt(edadStr, "edad").flatMap { edad ->
            checkMail(correoStr, "correo").map { correo ->
                Cliente(nombre, edad, correo)
            }
        }
    }


fun main() {
    println(construirCliente("", "23", "correcto@ucm.es"))
    println(construirCliente("Javier", "", "correcto@ucm.es"))
    println(construirCliente("Javier", "das", "correcto@ucm.es"))
    println(construirCliente("Javier", "32", "incorrecto@ucm@es"))
    println(construirCliente("Javier", "32", "correcto@ucm.es"))
}
