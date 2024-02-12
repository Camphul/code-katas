import nl.adesso.kgreed.Greed
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Created on 07/02/2024
 */
class GreedTest {

    @Test
    fun playGameWinPlayer0() {
        val plays = arrayOf(
            "1,2,3,4,5,6", // PLAYER 0: SCORE 1200,
            "2,2,3,3,4,4", // PLAYER 1: SCORE 800,
            "2,2,2,3,4,6", // PLAYER 0: SCORE 1400 WIN
        )
        val winner = Greed.playGame(1400) { play -> plays[play] }
        assertEquals(0, winner)
    }

    @Test
    fun playGameWinPlayer1() {
        val plays = arrayOf(
            "2,3,4,6,6,4", // PLAYER 0: SCORE 0
            "1,2,3,4,5,6", // PLAYER 1: SCORE 1200
            "2,2,2,3,4,6", // PLAYER 0: SCORE 200
            "2,2,2,3,4,6"  // PLAYER 1: SCORE 1400 WIN
        )
        val winner = Greed.playGame(1400) { play -> plays[play] }
        assertEquals(1, winner)
    }

    @Test
    fun testSingleOne() {
        val input = intArrayOf(1,2,2,3,4,6)
        val result = Greed.score(input)
        assertEquals(100, result)
    }

    @Test
    fun testTwoOnes() {
        val input = intArrayOf(1,1,2,3,4,6)
        val result = Greed.score(input)
        assertEquals(200, result)
    }

    @Test
    fun testThreeOnes() {
        val input = intArrayOf(1,1,1,3,4,6)
        val result = Greed.score(input)
        assertEquals(1000, result)
    }

    @Test
    fun testFourOnes() {
        val input = intArrayOf(1,1,1,1,4,6)
        val result = Greed.score(input)
        assertEquals(2000, result)
    }

    @Test
    fun testThreeTwos() {
        val input = intArrayOf(2,2,2,3,4,6)
        val result = Greed.score(input)
        assertEquals(200, result)
    }

    @Test
    fun testFourTwos() {
        val input = intArrayOf(2,2,2,2,4,6)
        val result = Greed.score(input)
        assertEquals(400, result)
    }

    @Test
    fun testFiveTwos() {
        val input = intArrayOf(2,2,2,2,2,6)
        val result = Greed.score(input)
        assertEquals(800, result)
    }

    @Test
    fun testSixTwos() {
        val input = intArrayOf(2,2,2,2,2,2)
        val result = Greed.score(input)
        assertEquals(1600, result)
    }

    @Test
    fun testZeroPoints() {
        val input = intArrayOf(2, 2, 3, 4, 3, 6)
        val result = Greed.score(input)
        assertEquals(0, result)
    }

    @Test
    fun testSingleFive() {
        val input = intArrayOf(5,2,2,3,4,6)
        val result = Greed.score(input)
        assertEquals(50, result)
    }

    @Test
    fun testTwoFives() {
        val input = intArrayOf(5,5,2,3,4,6)
        val result = Greed.score(input)
        assertEquals(100, result)
    }

    @Test
    fun testSingleOneAndSingleFive() {
        val input = intArrayOf(5,1,2,4,4,6)
        val result = Greed.score(input)
        assertEquals(150, result)
    }

    @Test
    fun testDistinctScore() {
        val input = intArrayOf(1,2,3,4,5,6)
        val result = Greed.score(input)
        assertEquals(1200, result)
    }

    @Test
    fun testThreePairs() {
        val input = intArrayOf(1,1,2,2,5,5)
        val result = Greed.score(input)
        assertEquals(800, result)
    }
}