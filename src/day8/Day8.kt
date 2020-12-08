package day8

import java.io.File
import java.lang.Exception

fun main() {
    val input = File("res/day8_input").readLines().map(String::trim)

    val candidates = input
            .mapIndexed{ i, s -> if (s.contains("nop") || s.contains("jmp")) i else null }
            .filterNotNull()

    candidates.forEach { index ->
        val modifiedInstructions = fixInstruction(index, input)
        run(0L, 0, modifiedInstructions, HashSet())?.let(::println)
    }
}

fun fixInstruction(index: Int, instructions:List<String>): List<String> {
    return instructions.mapIndexed{ i, s ->
        if (i == index) {
            if (s.contains("nop")) { s.replace("nop", "jmp") }
            else { s.replace("jmp", "nop") }
        }
        else s
    }
}

fun run(acc: Long, index:Int, instructions:List<String>, path: HashSet<Int>): Long? {
    return try {
        if (path.add(index).not()) {
            null
        } else {
            val (i, n) = instructions[index].split(" ", limit = 2)
            var newAcc = acc
            var newIndex = index
            when (i) {
                "acc" -> {
                    newAcc += Integer.parseInt(n); newIndex = index + 1
                }
                "nop" -> newIndex = index + 1
                "jmp" -> newIndex = index + Integer.parseInt(n)
            }
            run(newAcc, newIndex, instructions, path)
        }
    } catch (e: Exception) {
        return if (index == instructions.size) acc else null
    }
}