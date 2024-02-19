package nl.adesso.kata

/**
 * Created on 12/02/2024
 */
class InMemoryLibrary() : Library {

    private val registry = hashMapOf<Book,Int>()

    override fun registerBook(book: Book, totalCopies: Int) {
        if (registry.containsKey(book)) {
            throw IllegalStateException("Book ${book.info} already registered")
        }
        registry[book] =  totalCopies
    }

    override fun printBookRegistry() {
        registry.forEach { println("Book: ${it.key.info}, Total copies: ${it.value}") }
    }
}