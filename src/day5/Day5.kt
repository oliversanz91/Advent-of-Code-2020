package day5

import java.io.File

val testInput = """
    BFFFBBFRRR
    FFFBBBFRRR
    BBFFBBFRLL
""".trimIndent()

fun main() {
    //val input = testInput.split("\n").map(String::trim)
    val input = File("res/day5_input").readLines().map(String::trim)

    silverStar(input).let(::println)
    goldStar(input).let(::println)
}

fun silverStar(input: List<String>) = input.map {
    it.chunked(7, ::toBinary).map(::toNumber)
}.map(::getId).max()

fun goldStar(input: List<String>) = input.map {
    it.chunked(7, ::toBinary).map(::toNumber)
}.map(::getId).let { list ->
    // Si solo falta mi asiento tengo que buscar el primero que no exista desde donde estoy.
    ((list.max() ?: 0) downTo 0).first { it !in list }
}

fun toBinary(input: CharSequence) =
        input.toString()
                .replace('B', '1')
                .replace('F', '0')
                .replace('L', '0')
                .replace('R', '1')

fun toNumber(input: String) = Integer.parseInt(input, 2)

fun getId(input: List<Int>) = input[0] * 8 + input[1]