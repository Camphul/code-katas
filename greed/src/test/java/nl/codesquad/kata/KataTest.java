package nl.codesquad.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains test cases for the {@link Kata} class.
 *
 * @author Luca & Hugin
 * @since 22 January 2024
 */
class KataTest {

    /**
     * Single 1 should give score of 100.
     */
    @Test
    void testSingleOne() {
        int[] input = {2, 1, 3, 4, 4, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(100);
    }

    /**
     * Two 1's should give a score of 200.
     */
    @Test
    void testTwoOnes() {
        int[] input = {2, 1, 1, 4, 4, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(200);
    }

    /**
     * Certain inputs can still give a score of 0.
     */
    @Test
    void testZeroPoints() {
        int[] input = {2, 2, 3, 4, 3, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(0);
    }

    /**
     * A single 5 should return a score of 50.
     */

    @Test
    void testSingleFive() {
        int[] input = {2, 2, 3, 4, 5, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(50);
    }

    /**
     * Two 5's should return a score of 100.
     */
    @Test
    void testTwoFives() {
        int[] input = {2, 2, 5, 4, 5, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(100);
    }

    /**
     * A single 1 and a single 5 should return a score of 150.
     */
    @Test
    void testSingleOneAndSingleFive() {
        int[] input = {2, 1, 4, 4, 5, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(150);
    }

    /**
     * Triple test using 3's should return 300.
     * Other triples don't need to be tested due to the logic.
     */
    @Test
    void testTripleThree() {
        int[] input = {4, 6, 3, 2, 3, 3};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(300);
    }

    /**
     * Triple 1's should return a score of 1000.
     */
    @Test
    void testTripleOne() {
        int[] input = {4, 6, 1, 2, 1, 1};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(1000);
    }

    /**
     * Triple 1's and a single 5 should return 1050.
     */
    @Test
    void testTripleOneAndSingleFive() {
        int[] input = {5, 6, 1, 2, 1, 1};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(1050);
    }

    /**
     * Four 2's should return the triple score of 200 multiplied by 2.
     */
    @Test
    void testFourTwos() {
        int[] input = {2, 4, 2, 2, 6, 2};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(400);
    }

    /**
     * Five 2's should return the triple score of 200 multiplied by 4.
     */
    @Test
    void testFiveTwos() {
        int[] input = {2, 4, 2, 2, 2, 2};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(800);
    }

    /**
     * Six 2's should return the triple score of 200 multiplied by 8.
     */
    @Test
    void testSixTwos() {
        int[] input = {2, 2, 2, 2, 2, 2};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(1600);
    }

    /**
     * Four 1's should take the triple score of 1's and multiply by 2.
     */
    @Test
    void testFourOnes() {
        int[] input = {1, 4, 1, 1, 6, 1};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(2000);
    }

    /**
     * If all numbers are unique there's a constant score of 1200 that should be given.
     */
    @Test
    void testDistinctNumbers() {
        int[] input = {1, 2, 3, 4, 5, 6};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(1200);
    }

    /**
     * If there are three distinct numbers occurring in an input with 6 elements, a score of 800 should be given.
     * This must take priority above giving a score for two 1's.
     */
    @Test
    void testThreePairs() {
        int[] input = {1, 1, 2, 2, 5, 5};
        int score = Kata.score(input);
        assertThat(score).isEqualTo(800);
    }

    /**
     * Integers separated with commas and possible whitespace should be correctly converted into an integer array.
     */
    @Test
    void testStringToInputArray() {
        int[] expected = {2, 3, 5, 1, 4, 5};
        int[] result = Kata.parseInput("2,3, 5,1, 4, 5 ");
        assertThat(result).isEqualTo(expected);
    }

    /**
     * Player 0 should win the game due to obtaining 1400 points in the least amount of plays.
     */
    @Test
    void testPlayGameWinPlayer0() {
        final String[] plays = {
                "1,2,3,4,5,6", // PLAYER 0: SCORE 1200
                "2,2,3,3,4,4", // PLAYER 1: SCORE 800
                "2,2,2,3,4,6"  // PLAYER 0: SCORE 1400 WIN
        };
        int winner = Kata.playGame(1400, (play) -> plays[play]);
        assertThat(winner).isEqualTo(0);
    }

    /**
     * Player 1 should win the game due to obtaining 1400 points in the least amount of plays.
     */
    @Test
    void testPlayGameWinPlayer1() {
        final String[] plays = {
                "2,3,4,6,6,4", // PLAYER 0: SCORE 0
                "1,2,3,4,5,6", // PLAYER 1: SCORE 1200
                "2,2,2,3,4,6", // PLAYER 0: SCORE 200
                "2,2,2,3,4,6"  // PLAYER 1: SCORE 1400 WIN
        };
        int winner = Kata.playGame(1400, (play) -> plays[play]);
        assertThat(winner).isEqualTo(1);
    }
}