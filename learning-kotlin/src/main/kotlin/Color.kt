package nl.adesso.kata.learning

/**
 * Created on 13/02/2024
 */
enum class Color {
    RED, GREEN, BLUE, YELLOW, ORANGE, VIOLET, INDIGO;

    infix fun mix(b: Color) =
        when (setOf(this, b)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, VIOLET) -> INDIGO
            else -> throw Exception("Ugly color!")
        }
}