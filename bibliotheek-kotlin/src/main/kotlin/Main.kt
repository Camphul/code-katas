package nl.adesso.kata

/**
 * Created on 12/02/2024
 */
fun main() {
    println("Hello World!")
    val book1 = Book("1234", "My first book")
    val book2 = Book("2345", "My second book")
    val book3 = Book("3456", "My third book")

    println(book1.summary())
    println(book1.info)
    val library: Library = InMemoryLibrary()
    library.registerBook(book1, 5)
    library.registerBook(book = book2, totalCopies = 4)
    library.registerBook(book3)
    library.printBookRegistry()
}