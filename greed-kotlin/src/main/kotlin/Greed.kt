package nl.adesso.kgreed

/**
 * Created on 07/02/2024
 */
class Greed {

    companion object {
        private fun IntArray.occurrences(value: Int) = count { it == value }
        fun playGame(scoreGoal: Int, inputFunction: (play: Int) -> String): Int {
            var currentPlayer = 0;
            val scores = intArrayOf(0,0)
            var plays = 0
            while (true) {
                val line = inputFunction(plays)
                val input = line.replace("\\s+", "").split(",").map { it.toInt() }.toIntArray()
                val score = score(input)
                scores[currentPlayer] = scores[currentPlayer] +  score
                println("[play $plays] player $currentPlayer score: ${scores[currentPlayer]}")
                if (scores[currentPlayer] >= scoreGoal) {
                    return currentPlayer
                }
                plays++
                currentPlayer = (currentPlayer + 1) % 2
            }
        }

        fun score(input: IntArray) = when {
            (input.distinct().size == 6) -> 1200
            (input.distinct().size == 3 && input.all { input.occurrences(it) == 2 }) -> 800
            else -> input.distinct().map { it to input.occurrences(it) }.sumOf { (number, occurrences) ->
                when {
                    // first = the number
                    // second = the occurrences of number
                    (number in setOf(1, 5) && occurrences < 3) -> occurrences * if (number == 1) 100 else 50
                    occurrences >= 3 -> (if (number == 1) 1000 else (number * 100)) * (1 shl (occurrences - 3))
                    else -> 0
                }
            }

        }
    }
}