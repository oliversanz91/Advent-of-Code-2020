package day6

import day4.UNEXPECTED_VALUE
import java.io.File

fun main() {
    val silverInput = File("res/day6_input").readLines()
            .joinToString(separator = UNEXPECTED_VALUE, transform = String::trim)
            .split("$UNEXPECTED_VALUE$UNEXPECTED_VALUE")
            .map { it.replace(UNEXPECTED_VALUE, "") }

    val goldInput = File("res/day6_input").readLines()
            .joinToString(separator = UNEXPECTED_VALUE, transform = String::trim)
            .split("$UNEXPECTED_VALUE$UNEXPECTED_VALUE")
            .map{ it.split(UNEXPECTED_VALUE) }

    silverStar(silverInput).let(::println)
    goldStar(goldInput).let(::println)
}

private fun silverStar(input: List<String>) =
        input.map{ group -> group.groupBy { it }.size }.sum()

private fun goldStar(input: List<List<String>>) =
        input.map{ group -> group.joinToString(separator = "").trim().groupBy { it }.count { it.value.size == group.size } }.sum()