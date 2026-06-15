import java.util.Random;
import java.util.Scanner;

/**
 * Number Guessing Game
 * --------------------
 * The computer generates a random number within a given range.
 * The user has a limited number of attempts per round to guess it.
 * Points are awarded based on how quickly the number is guessed,
 * and a total score is tracked across multiple rounds.
 *
 * Author: Bhumika Srivastava
 * Task: OIBSIP - Task 2 (Number Guessing Game)
 */
public class NumberGuessingGame {

    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        boolean playAgain = true;
        int round = 1;

        System.out.println("=========================================");
        System.out.println("      WELCOME TO NUMBER GUESSING GAME    ");
        System.out.println("=========================================");
        System.out.println("Rules:");
        System.out.println("- I will think of a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");
        System.out.println("- You have " + MAX_ATTEMPTS + " attempts to guess it.");
        System.out.println("- Fewer attempts used = higher score!");
        System.out.println("=========================================\n");

        while (playAgain) {
            System.out.println("----- ROUND " + round + " -----");
            int numberToGuess = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.print("Attempts left: " + attemptsLeft + ". Enter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < MIN_RANGE || guess > MAX_RANGE) {
                    System.out.println("Please enter a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");
                    continue;
                }

                if (guess == numberToGuess) {
                    int pointsEarned = attemptsLeft * 10;
                    totalScore += pointsEarned;
                    System.out.println("Congratulations! You guessed it right!");
                    System.out.println("You earned " + pointsEarned + " points this round!");
                    guessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.println("Current Total Score: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
            round++;
            System.out.println();
        }

        System.out.println("=========================================");
        System.out.println("Thanks for playing! Your final score: " + totalScore);
        System.out.println("=========================================");

        scanner.close();
    }
}