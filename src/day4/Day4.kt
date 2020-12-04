package day4

import java.io.File

const val UNEXPECTED_VALUE = ";;;;;"

fun main() {
    File("res/day4_input")
            .readLines().joinToString(separator=UNEXPECTED_VALUE)
            .split("$UNEXPECTED_VALUE$UNEXPECTED_VALUE")
            .map { it.replace(UNEXPECTED_VALUE, " ") }
            .map(::validatePassport)
            .count { it }
            .let(::println)
}

private fun validatePassport(passport: String): Boolean {
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    return requiredFields.all { it in passport.split(" ").map(::getKey) }
            && passport.split(" ").all(::validateData)
}

private fun getKey(data: String) = data.substringBefore(":")
private fun validateData(data: String) : Boolean {
    val (key, value) = data.split(":", limit = 2)
    return when (key) {
        "byr" -> (value.length == 4 && value.toInt() in 1920..2002)
        "iyr" -> (value.length == 4 && value.toInt() in 2010..2020)
        "eyr" -> (value.length == 4 && value.toInt() in 2020..2030)
        "hgt" -> {
            (value.endsWith("cm") && value.substringBefore("cm").toInt() in 150..193) ||
                    (value.endsWith("in") && value.substringBefore("in").toInt() in 59..76)
        }
        "hcl" -> colorValidator(value)
        "ecl" -> value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        "pid" -> (value.length == 9)
        else -> true
    }
}

private fun colorValidator(color: String): Boolean {
    return (
            color.length == 7 &&
            color.startsWith("#") &&
            color.substringAfter("#").all { it in '0'..'9' || it in 'a'..'f' }
            )
}
