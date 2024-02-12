package nl.adesso.kgreed

/**
 * Created on 07/02/2024
 */
class Greed {
    companion object {
        fun countOccurrences(input: IntArray, num: Int): Int {
            return input.count { it == num }
        }
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

        fun score(input: IntArray): Int {
            if (input.distinct().size == 6) {
                return 1200
            }
            if (input.distinct().size == 3 && input.all { countOccurrences(input, it) == 2 }) {
                return 800
            }
            var totalScore = 0
            numberLoop@ for (number in input.distinct()) {
                val occurrences = countOccurrences(input, number)
                if ((number == 1 || number == 5) && occurrences < 3) {
                    totalScore += occurrences * (if(number == 1) 100 else 50)
                } else {
                    if (occurrences < 3) {
                        continue@numberLoop
                    }
                    var tripleScore = if (number == 1) 1000 else (number * 100)
                    val multiplier = 1 shl (occurrences - 3)
                    tripleScore *= multiplier
                    totalScore += tripleScore
                }
            }
            return totalScore
        }
    }
}