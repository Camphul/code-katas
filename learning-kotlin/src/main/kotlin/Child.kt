package nl.adesso.kata.learning

/**
 * Created on 14/02/2024
 */
class Child: Parent() {

    override fun whoami() {
        println("I am child!")
    }
}