package nl.adesso.kata.learning

import nl.adesso.kata.learning.extensions.lastChar
import nl.adesso.kata.learning.extensions.repeat

/**
 * Created on 19/02/2024
 */
fun main() {
    println("Hello World!")
    updateWeatherDegrees(3)
    updateWeatherDegrees(20)
    updateWeatherDegrees(30)
    println("Is 30 hot: ${isHot(30)}")
    println("Is 15 hot: ${isHot(15)}")

    if(max(5,6) == 6) {
        println("Corect! Max is b: 6")
    }
    if(max(10,6) == 10) {
        println("Corect! Max is a: 10")
    }
    if(max(b=6,a=10) == 10) {
        println("Corect! Max is a: 10")
    }
    displaySeparator()
    displaySeparator('#')
    displaySeparator('#', 5)
    displaySeparator(size =  5)

    println("Red with yellow  is: ${ Color.RED mix Color.YELLOW}")

    for (i in '0' .. '9')
        println(i)

    println("a is letter: ${isLetter('a')}")
    println("D is letter: ${isLetter('D')}")
    println("4 is letter: ${isLetter('4')}")

    val number = try {
        "4zzz".toInt()
    } catch (e: NumberFormatException) {
        0
    }

    println("Parsed int is: $number")

    println("Beep last char: ${"Beep".lastChar()}")

    println("beep".repeat(3))

    val strArr = arrayOf("Foo", "Bar", "Ding", "Dong", "Foo", "Bar", "Bar")
    println("Bar occurrences: ${strArr.occurrences("Bar")}")


    val multiline = """
        Ding dong
        I am a 
        multi-line 
        string
    """.trimIndent()
    println(multiline)

    val thing: Parent = Child()
    thing.whoami()

    val thing2 = Child()
    thing2.whoami()

    fun Parent.foo() {
        println("Foo from parent")
    }

    fun Child.foo() {
        println("Foo from child")
    }

    thing.foo()
    thing2.foo()
    playMasterMindGame()

    println("is \"_value\" valid identifier: ${isValidIndentifier("_value")}")
    println("is \"\$value\" valid identifier: ${isValidIndentifier("\$value")}")
    println("is \"_12\" valid identifier: ${isValidIndentifier("_12")}")
    println("is \"012\" valid identifier: ${isValidIndentifier("012")}")
    val sum = listOf(3, 7, 5).calcSum()
    println(sum)

    var ding: String? = null

    println(ding?.length)

    ding = "Dong"
    println(ding?.length)

    var demo: String? = null


    println(demo ?: "default-value")

    val ahh: Any = "Beep"
    if (ahh is String) {
        println(ahh.length)
    }
}

fun List<Int>.calcSum(): Int {
    return  sum()
}

fun isValidIndentifier(s: String): Boolean {
    fun isValidCharacter(ch: Char) =
        ch == '_' || ch.isLetterOrDigit()

    if (s.isEmpty() || s[0].isDigit()) return false
    return s.all{isValidCharacter(it)}
}

fun <T> Array<T>.occurrences(value: T) = count { it == value }

fun isLetter(letter: Char) = letter in 'a' .. 'z' || letter in 'A' .. 'Z'

fun isHot(tmp: Int) = tmp > 25

fun max(a: Int, b: Int) = if (a > b) a else b

fun displaySeparator(character: Char = '*', size: Int = 10) {
    repeat(size) {
        print(character)
    }
    println()
}

fun updateWeatherDegrees(temp: Int) {
    val(description: String, color: Color) =
        when {
            temp < 10 -> {
                "Cold" to Color.BLUE
            }
            temp < 25 -> {
                "Mild" to Color.ORANGE
            }
            else -> {
                "Hot" to Color.RED
            }
        }

    println("Weather description: $description and color: $color")
}