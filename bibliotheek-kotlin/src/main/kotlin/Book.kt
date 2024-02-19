package nl.adesso.kata

/**
 * Created on 12/02/2024
 */
data class Book(val isbn: String, val title: String)
val Book.info: String
    get() = "isbn: $isbn: title: $title"
fun Book.summary(): String {
    return "$isbn: $title"
}