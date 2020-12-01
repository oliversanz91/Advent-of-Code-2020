package day1

import java.io.File

fun main() {
    val testInput = listOf(1721, 979, 366, 299, 675, 1456)
    val puzzleInput = File("res/day1_input").readLines().map(String::trim).map(String::toInt)
    val res = day1Extra(puzzleInput)
    println("res=$res")
}

private fun day1(input: List<Int>): Int {
    return input.map { first -> input.map { second -> Pair(first, second) } }.flatten()
            .first { ((it.first + it.second) == 2020) }
            .let { it.first * it.second }
}

private fun day1Extra(input: List<Int>): Int {
    return input.map { first -> input.map { second -> input.map { third -> Triple(first, second, third) } }.flatten() }.flatten()
            .first { ((it.first + it.second + it.third) == 2020) }
            .let { it.first * it.second * it.third }
}