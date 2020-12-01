
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File

fun main() = runBlocking<Unit> {

    val a = async {
        println("I'm computing part of the answer")
        6
    }
    val b = async {
        println("I'm computing another part of the answer")
        7
    }
    test()
    println("The answer is ${a.await() * b.await()}")
    println("Testing Advent of Code 20202")
}

private suspend fun test() {
    delay(1500)
    println("Test...")
}