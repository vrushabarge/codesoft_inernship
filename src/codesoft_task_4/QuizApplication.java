/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codesoft_task_4;

/**
 *
 * @author Adming
 */
import java.util.Scanner;
import java.util.concurrent.*;

public class QuizApplication {
    public static void main(String[] args) {
        // Quiz questions, options, and correct answers
        try (Scanner scanner = new Scanner(System.in)) {
            // Quiz questions, options, and correct answers
            String[][] quiz = {
                {"What is the capital of France?", "A. Paris", "B. London", "C. Berlin", "D. Rome", "A"},
                {"Which planet is known as the Red Planet?", "A. Earth", "B. Mars", "C. Jupiter", "D. Saturn", "B"},
                {"What is 2 + 2?", "A. 3", "B. 4", "C. 5", "D. 6", "B"},
                {"Who wrote 'Romeo and Juliet'?", "A. Charles Dickens", "B. William Shakespeare", "C. Mark Twain", "D. Jane Austen", "B"}
            };
            
            int score = 0;
            int totalQuestions = quiz.length;
            boolean[] correctAnswers = new boolean[totalQuestions];
            
            System.out.println("Welcome to the Quiz! You have 10 seconds to answer each question.\n");
            
            for (int i = 0; i < totalQuestions; i++) {
                System.out.println("Question " + (i + 1) + ": " + quiz[i][0]);
                for (int j = 1; j <= 4; j++) {
                    System.out.println(quiz[i][j]);
                }
                
                // Timer implementation
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<String> future = executor.submit(() -> {
                    System.out.print("Your answer (A/B/C/D): ");
                    return scanner.nextLine().toUpperCase();
                });
                
                String userAnswer = "";
                try {
                    userAnswer = future.get(10, TimeUnit.SECONDS); // 10-second timer
                } catch (TimeoutException e) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    future.cancel(true);
                } catch (InterruptedException | ExecutionException e) {
                } finally {
                    executor.shutdown();
                }
                
                // Check answer
                if (userAnswer.equals(quiz[i][5])) {
                    score++;
                    correctAnswers[i] = true;
                    System.out.println("Correct!\n");
                } else if (!userAnswer.isEmpty()) {
                    correctAnswers[i] = false;
                    System.out.println("Incorrect! The correct answer is " + quiz[i][5] + ".\n");
                }
            }
            
            // Display results
            System.out.println("Quiz Over!");
            System.out.println("Your Final Score: " + score + "/" + totalQuestions);
            System.out.println("\nSummary of Answers:");
            for (int i = 0; i < totalQuestions; i++) {
                System.out.println("Question " + (i + 1) + ": " + (correctAnswers[i] ? "Correct" : "Incorrect"));
            }
        }
    }
}
