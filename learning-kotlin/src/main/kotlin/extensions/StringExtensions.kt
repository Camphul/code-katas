package nl.adesso.kata.learning.extensions

/**
 * Created on 13/02/2024
 */

fun String.lastChar() = get(length-1)

fun String.repeat(n: Int): String {
    val sb = StringBuilder(n * length)
    repeat(n) {
        sb.append(this)
    }
    return sb.toString()
}