package day3

import java.io.File

typealias Slope = Pair<Int, Int>

fun main() {
    //val input = File("res/day3_test_input").readLines().map(String::trim)
    val input = File("res/day3_input").readLines().map(String::trim)
    val height = input.size
    val width = input.first().length

    listOf(Slope(1, 1), Slope(1, 3), Slope(1, 5), Slope(1, 7), Slope(2, 1)).map {
        path(input, it.first, it.second, height, width).also { println(it) }
    }.fold(1L, { acc, value -> acc * value }).let(::println)

}

fun path(map: List<String>, down: Int, right: Int, height: Int, width: Int): Int {
    return (0 until height step down)
            .mapIndexed { index: Int, it: Int -> map[it][(index*right).rem(width)] }.count { it == '#' }
}
