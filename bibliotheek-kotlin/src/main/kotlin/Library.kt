package nl.adesso.kata

/**
 * Created on 12/02/2024
 */
interface Library {

    fun registerBook(book: Book, totalCopies: Int = 10)

    fun printBookRegistry()
}