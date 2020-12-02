
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import java.io.File

fun main() = runBlocking<Unit> {
    joinAll(async { test() }, async { test(200) })
    println("Testing Advent of Code 20202")
}

private suspend fun test(time: Long = 1500 ) {
    delay(time)
    println("Test... $time")
}