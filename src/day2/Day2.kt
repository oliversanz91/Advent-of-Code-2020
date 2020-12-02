package day2

import java.io.File

data class Line(val pos1: Int, val pos2: Int, val character: Char, val password: String){
    constructor(input: List<String>): this(input[0].toInt(), input[1].toInt(), input[2][0], input[3])
}

fun main() {
    val testInput = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
    """.trimIndent().split("\n").map(String::trim)
    val puzzleInput = File("res/day2_input").readLines().map(String::trim)
    testInput.map(::processLine).map(::tobboganCorporateValidator).count { it }.let(::println)
}

val SEPARATORS = listOf("-", " ", ": ").toTypedArray()
fun processLine(line: String) = line.split(delimiters = *SEPARATORS, limit = 4).let(::Line)

fun tobboganCorporateValidator(line: Line): Boolean {
    return with(line) { (password[pos1-1] == character).xor((password[pos2-1] == character)) }
}

fun oldJobValidator(line: Line): Boolean {
    return with(line) { password.count { it == character }.run{ this in pos1..pos2 } }
}
