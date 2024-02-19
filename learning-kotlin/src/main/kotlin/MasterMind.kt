package nl.adesso.kata.learning

import java.util.*

/**
 * Created on 14/02/2024
 */

data class Evaluation(val rightPosition: Int, val wrongPosition: Int) {

}

class Assertion<T>(private val target: T) {
    infix fun eq(other: T) {
        println("Eval other: $other, target: $target")
       assert(Objects.equals(other, target))
    }
}
fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPass = secret.zip(guess).count { it.first.equals(it.second) }
    val commonLetters = "ABCDEF".sumOf { ch -> Math.min(secret.count{ ch == it}, guess.count{ ch == it}) }
    return Evaluation(rightPass, commonLetters - rightPass)
}

fun playMasterMindGame() {
    val result = Evaluation(1,1)
    Assertion(evaluateGuess("BCDF", "ACEB")) eq result
    Assertion(evaluateGuess("AAAF", "ABCA")) eq result
    Assertion(evaluateGuess("ABCA", "AAAF")) eq result
}