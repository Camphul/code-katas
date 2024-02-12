package nl.codesquad.kata;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * CodeKata implementation for <a href="https://codingdojo.org/kata/Greed/">Greed</a>
 *
 * @author Luca & Hugin
 * @since 22 january 2024
 */
public class Kata {
    /**
     * Dice domain (six sided dice)
     */
    private static final int[] DOMAIN = {1, 2, 3, 4, 5, 6};

    /**
     * Score to give when each element in input is unique
     */
    private static final int DISTINCT_SCORE = 1200;

    /**
     * Score to give when an input of 6 integers contain 3 distinct integers that occur twice
     * Example: {2,2,3,3,4,4}
     */
    private static final int PAIRS_SCORE = 800;

    /**
     * Score to assign for single occurrence of 1
     */
    private static final int SINGLE_ONE_SCORE = 100;

    /**
     * Score to assign for single occurrence of 5
     */
    private static final int SINGLE_FIVE_SCORE = 50;

    /**
     * Plays a CLI game of greed with two players.
     *
     * @param args program args
     */
    public static void main(String[] args) {
        final int SCORE_GOAL = 4000;
        var scanner = new Scanner(System.in);
        System.out.printf("Game start, score goal: %d\n", SCORE_GOAL);
        int winner = playGame(SCORE_GOAL, (play) -> {
            System.out.println("Enter numbers:");
            return scanner.nextLine();
        });
        System.out.printf("Game end, winner player %d\n", winner);
    }

    /**
     * Play a game.
     *
     * @param scoreGoal     the score that should be achieved.
     * @param inputFunction the integer is the current play count, should return a string with input.
     * @return the winning player, 0 or 1
     */
    public static int playGame(int scoreGoal, Function<Integer, String> inputFunction) {
        int[] scores = {0, 0};
        int currentPlayer = 0;
        int plays = 0;
        while (true) {
            String line = inputFunction.apply(plays);
            int[] input = parseInput(line);
            int score = score(input);
            scores[currentPlayer] = scores[currentPlayer] + score;
            System.out.printf("Play %d: score player %d: %d\n", plays, currentPlayer, scores[currentPlayer]);
            if (scores[currentPlayer] >= scoreGoal) {
                return currentPlayer;
            }

            plays++;
            // switch between player 0 and player 1
            currentPlayer = (currentPlayer + 1) % 2;
        }
    }


    /**
     * Calculates the score for a given input array of dice values according to a specific scoring system.
     *
     * @param input the input array of dice values
     * @return the calculated score based on the input values
     */
    public static int score(int[] input) {
        assert input.length == 6;

        int[] distinct = Arrays.stream(input).distinct().toArray();

        // Check for distinct numbers
        if (distinct.length == DOMAIN.length) {
            return DISTINCT_SCORE;
        }

        // Check for pairs such as {2,2,3,3,4,4}
        if (hasThreeDistinctPairs(input, distinct)) {
            return PAIRS_SCORE;
        }

        // Total score
        int total = 0;

        // Calculate score for each distinct number
        for (int diceValue : distinct) {
            int occurrences = countOccurrences(input, diceValue);

            switch (diceValue) {
                case 1:
                    if (occurrences < 3) {
                        total += SINGLE_ONE_SCORE * occurrences;
                        break;
                    }
                case 5:
                    if (occurrences < 3) {
                        total += SINGLE_FIVE_SCORE * occurrences;
                        break;
                    }
                default:
                    total += calculateTripleScore(diceValue, occurrences);
            }
        }

        return total;
    }

    /**
     * Check if input contains 3 distinct numbers that each occur twice.
     *
     * @param input    input numbers
     * @param distinct distinct numbers in input
     * @return if 3 distinct numbers occur twice in input
     */
    private static boolean hasThreeDistinctPairs(int[] input, int[] distinct) {
        // pre-check before looping and matching (performance)
        if (distinct.length != 3) {
            return false;
        }
        return Arrays.stream(distinct).allMatch(num -> countOccurrences(input, num) == 2);
    }

    /**
     * Calculate the triple score including the multipliers
     *
     * @param diceValue   current number to check score on
     * @param occurrences amount of occurrences for diceValue
     * @return score associated with diceValue
     */
    private static int calculateTripleScore(int diceValue, int occurrences) {
        int tripleScore = 0;
        if (occurrences >= 3) {
            tripleScore = (diceValue == 1) ? 1000 : diceValue * 100;
            tripleScore *= calculateMultiplier(occurrences);
        }
        return tripleScore;
    }

    /**
     * Get the multiplier based on the number of occurrences.
     * Because 3,4,5,6 are all exponential multipliers, we distract 3 from the occurrences to be able to take it to the power of 2.
     * Because 2^(3-3) = 2^0 = 1, 2^(4-3) = 2^1 = 2, 2^(5-3)= 2^2 = 4, etc....
     *
     * @param occurrences the number of occurrences
     * @return the multiplier for calculating scores
     */
    private static int calculateMultiplier(int occurrences) {
        return 1 << (occurrences - 3); // 2 ^ (occurrences - 3) / Math.pow(2, occurrences-3)
    }

    /**
     * Counts the number of occurrences of a given number in an array.
     *
     * @param input  the input array of numbers
     * @param number the number to count occurrences of
     * @return the number of occurrences of the given number in the array
     */
    private static int countOccurrences(int[] input, int number) {
        return (int) Arrays.stream(input).filter((num) -> num == number).count();
    }

    /**
     * Parses a string representation of input into an array of integers.
     * The input  is a comma-separated string of integers with possible whitespace.
     * Example valid input is (without quotes): " 1,  2,3 , 4 ,5,6   "
     *
     * @param input the string representation of input
     * @return an array of integers parsed from the input
     */
    public static int[] parseInput(String input) {
        return Arrays.stream(input.replaceAll("\\s+", "").split(",")).mapToInt(Integer::valueOf).toArray();
    }
}
