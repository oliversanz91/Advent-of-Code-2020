package day9

import java.io.File
import kotlin.math.abs

const val preambleSize = 25
fun main() {
    val input = File("res/day9_input").readLines().map(String::trim).map(String::toLong)

    val needle = silverStar(input)
    println(goldStar(needle, input))
}

fun isValid(number: Long, preamble: List<Long>): Boolean {
    return (preamble.map { abs(number - it) }).any { it in preamble }
}

private fun goldStar(needle: Long, hayStack: List<Long>): Long {
    var currentSum = 0L
    var leftIndex = 0
    var rightIndex = 0
    while (rightIndex < hayStack.size && currentSum != needle) {
        when {
            currentSum < needle -> rightIndex += 1
            currentSum > needle -> leftIndex += 1
        }
        currentSum = hayStack.subList(leftIndex, rightIndex+1).sum()
    }
    return hayStack.subList(leftIndex, rightIndex+1).let { (it.max() ?: 0L) + (it.min() ?: 0L) }
}

private fun silverStar(input: List<Long>) : Long {
    return input[preambleSize + (preambleSize until input.lastIndex).indexOfFirst { isValid(input[it], input.subList(it- preambleSize, it)).not() }]
}