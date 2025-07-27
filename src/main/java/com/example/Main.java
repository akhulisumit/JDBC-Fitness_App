package com.example;

import com.example.dao.*;
import com.example.entity.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== Demo: Database Connectivity Check ===");
            try (java.sql.Connection conn = DBConnection.getConnection()) {
                if (conn != null && !conn.isClosed()) {
                    System.out.println("Successfully connected to the database.");
                } else {
                    System.out.println("Failed to connect to the database.");
                    return; // Stop further tests if connection fails
                }
            }
            System.out.println();

            UserDAO userDAO = new UserDAOImpl();
            WorkoutDAO workoutDAO = new WorkoutDAOImpl();
            ExerciseDAO exerciseDAO = new ExerciseDAOImpl();
            ProgressDAO progressDAO = new ProgressDAOImpl();

            List<User> allUsers = userDAO.getAllUsers();

            if (allUsers.isEmpty()) {
                System.out.println("No users found in database.");
                return;
            }

            System.out.println("=== Listing all users and their related demo data ===");
            for (User user : allUsers) {
                System.out.println("\n--- User ID " + user.getId() + ": " + user.getName() + " ---");

                // List Workouts in Past Month
                List<Workout> workouts = workoutDAO.getWorkoutsForUserPastMonth(user.getId());
                System.out.println("  Workouts in the past month (" + workouts.size() + " found):");
                if (workouts.isEmpty()) {
                    System.out.println("    No recent workouts.");
                } else {
                    for (Workout w : workouts) {
                        System.out.println("    Workout ID " + w.getId() + " on " + w.getDate());

                        // List Exercises for this Workout
                        List<Exercise> exercises = exerciseDAO.getExercisesForWorkout(w.getId());
                        if (exercises.isEmpty()) {
                            System.out.println("      No exercises for this workout.");
                        } else {
                            System.out.println("      Exercises:");
                            for (Exercise ex : exercises) {
                                System.out.println("        - " + ex.getName() + " (" + ex.getType() + ")");
                            }
                        }
                    }
                }

                // Show weekly workout durations for last 3 months
                Map<String, Integer> weekDurations = workoutDAO.getWeeklyDurations(user.getId());
                if (weekDurations.isEmpty()) {
                    System.out.println("  No workout durations recorded in the last 3 months.");
                } else {
                    System.out.println("  Weekly workout durations (minutes) in last 3 months:");
                    weekDurations.forEach(
                            (week, minutes) -> System.out.println("    Week " + week + ": " + minutes + " minutes"));
                }

                // Show weight progress entries
                List<Progress> progressEntries = progressDAO.getWeightEntries(user.getId());
                if (progressEntries.isEmpty()) {
                    System.out.println("  No progress (weight) entries.");
                } else {
                    System.out.println("  Weight progress entries:");
                    for (Progress p : progressEntries) {
                        System.out.println("    " + p.getLogTime() + ": " + p.getWeightKg() + " kg");
                    }
                }

                // Show most frequent exercise type in last 2 months
                LocalDate today = LocalDate.now();
                Date toDate = Date.valueOf(today);
                Date fromDate = Date.valueOf(today.minusMonths(2));
                String mostFreqType = exerciseDAO.getMostFrequentExerciseType(user.getId(), fromDate, toDate);
                if (mostFreqType != null) {
                    System.out.println("  Most frequent exercise type (last 2 months): " + mostFreqType);
                } else {
                    System.out.println("  No exercise data found for last 2 months.");
                }
            }

            System.out.println("\n=== Demo complete ===");

        } catch (Exception e) {
            System.err.println("An error occurred during demo:");
            e.printStackTrace();
        }
    }
}
