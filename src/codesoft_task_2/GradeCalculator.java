/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codesoft_task_2;

import java.util.Scanner;

/**
 *
 * @author Admin
 */

public class GradeCalculator {
    public static void main(String[] args) {
        // Input: Number of subjects
        try (Scanner scanner = new Scanner(System.in)) {
            // Input: Number of subjects
            System.out.print("Enter the number of subjects: ");
            int numSubjects = scanner.nextInt();
            
            // Input: Marks obtained in each subject
            int[] marks = new int[numSubjects];
            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
                marks[i] = scanner.nextInt();
            }
            
            // Calculate Total Marks
            int totalMarks = 0;
            for (int mark : marks) {
                totalMarks += mark;
            }
            
            // Calculate Average Percentage
            double averagePercentage = (double) totalMarks / numSubjects;
            
            // Grade Calculation
            String grade;
            if (averagePercentage >= 90) {
                grade = "A+";
            } else if (averagePercentage >= 80) {
                grade = "A";
            } else if (averagePercentage >= 70) {
                grade = "B";
            } else if (averagePercentage >= 60) {
                grade = "C";
            } else if (averagePercentage >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }
            
            // Display Results
            System.out.println("\nResults:");
            System.out.println("Total Marks: " + totalMarks);
            System.out.println("Average Percentage: " + averagePercentage + "%");
            System.out.println("Grade: " + grade);
        }
    }
}

