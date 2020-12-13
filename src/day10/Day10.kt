package day10

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("res/day10_input").readLines().map(String::trim).map(String::toInt)

    println(silverStar(input))
    println(goldStar(input))
}

private fun silverStar(input: List<Int>): Int {
    return input.sorted()
            .filterIndexed { index, item -> index == 0 || (item - input[index-1] <= 3) }
            .mapIndexed { index, item -> if (index == 0) item else item - input[index-1] }
            .let { Pair(it.count { it == 1 }, it.count { it == 3 } +1) }
            .let { it.first * it.second }

}

private val map = mutableMapOf<Int, Long>()
private fun goldStar(input: List<Int>): Long {
    val end = (input.max() ?: 0) +3
    listOf(end-1, end-2, end-3).forEach { if (it in input) { map[it] = 1 } }
    input.sortedDescending().toMutableList().apply { this.add(0, end) }.forEach{ item ->
        map[item] = map.getOrDefault(item, 0L) + listOf(item +1, item +2, item+3 ).map { map.getOrDefault(it, 0L) }.sum()
    }
    return listOf(0, 1, 2, 3).map { map.getOrDefault(it, 0L) }.sum()
}



