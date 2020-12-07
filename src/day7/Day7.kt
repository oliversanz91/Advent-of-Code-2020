package day7

import java.io.File

fun main() {
    val input = File("res/day7_input")
            .readLines()
            .map(String::trim)
            .map(::extractGoldKey)
            .map { it[0] to it[1].replace(",", "+") }.toMap().toMutableMap()


    // Y esto a google a que me diga el resultado de la operación
    trimData(input)["shinygold"]?.replace("(", "(1+").let(::println)
}

fun silverStar() {
    val input = File("res/day7_input").readLines().map(String::trim)
            .map(::extractKey)
            .map { it[0] to it[1].split(",") }.toMap()


    input.map {
        isPathTo("shinygold", listOf(it.key), input)
    }.count { it }.let(::println)

}

private fun isPathTo(dest: String, currentPath: List<String>, map: Map<String, List<String>>): Boolean {
    val nextSteps = map[currentPath.last()]
    if (nextSteps?.contains(dest) == true){
        return true
    }
    else {
        if (nextSteps?.minus(currentPath).isNullOrEmpty()) {
            return false
        }
        else {
            return nextSteps!!.any {
                isPathTo(dest, currentPath.toMutableList().apply { this.add(it) }, map)
            }
        }
    }
}

private fun extractKey(line: String): List<String> {
    val regex = "[0-9\\. ]|(bags?)|(no other)".toRegex()
    return line.split("contain", limit = 2).map { it.replace(regex, "").trim() }
}

private fun extractGoldKey(line: String): List<String> {
    val regex = "[\\. ]|(bags?)".toRegex()
    return line.split("contain", limit = 2).map {
        it.replace("no other bags", "0").replace(regex, "").trim()
    }
}

private fun trimData(unProcessedData: MutableMap<String, String>): Map<String, String>{
    if (unProcessedData.values.none { it.contains("[a-z]".toRegex()) }){
        return unProcessedData
    }
    else {
        val data = unProcessedData.map {
            var newValue = it.value
            unProcessedData.keys.forEach { key ->
                newValue = newValue.replace(key, "(${unProcessedData[key]})")
            }
            Pair(it.key, newValue)
        }.toMap().toMutableMap()

        return trimData(data)
    }
}