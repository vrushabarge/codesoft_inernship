/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codesoft_task_1;

/**
 *
 * @author Admin
 */
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            int minRange = 1;
            int maxRange = 100;
            int maxAttempts = 10;
            int roundsWon = 0;
            int totalAttempts = 0;
            boolean playAgain = true;
            
            System.out.println("Welcome to the Number Guessing Game!");
            
            while (playAgain) {
                int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
                System.out.println("\nI've picked a number between " + minRange + " and " + maxRange + ". You have " + maxAttempts + " attempts to guess it.");
                
                int attempts = 0;
                boolean guessedCorrectly = false;
                
                while (attempts < maxAttempts && !guessedCorrectly) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;
                    
                    if (userGuess == randomNumber) {
                        System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                        guessedCorrectly = true;
                        roundsWon++;
                        totalAttempts += attempts;
                    } else if (userGuess < randomNumber) {
                        System.out.println("Too low! Try again.");
                    } else {
                        System.out.println("Too high! Try again.");
                    }
                }
                
                if (!guessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
                }
                
                System.out.print("Do you want to play again? (yes/no): ");
                String playAgainResponse = scanner.next().toLowerCase();
                if (!playAgainResponse.equals("yes")) {
                    playAgain = false;
                }
            }
            
            System.out.println("\nGame Over!");
            System.out.println("Rounds Won: " + roundsWon);
            System.out.println("Total Attempts: " + totalAttempts);
            if (roundsWon > 0) {
                System.out.println("Average Attempts per Round: " + (double) totalAttempts / roundsWon);
            }
        }
    }
}