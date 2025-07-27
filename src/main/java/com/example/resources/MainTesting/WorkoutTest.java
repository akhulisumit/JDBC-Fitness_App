package com.example.resources.MainTesting;
//Lists workouts for a user in the past month.

import com.example.dao.WorkoutDAO;
import com.example.dao.WorkoutDAOImpl;
import com.example.entity.Workout;

import java.util.List;

public class WorkoutTest {
    public static void main(String[] args) {
        try {
            long testUserId = 1; // Replace with actual user ID

            WorkoutDAO workoutDAO = new WorkoutDAOImpl();
            List<Workout> workouts = workoutDAO.getWorkoutsForUserPastMonth(testUserId);

            System.out.println("Workouts for user ID " + testUserId + " in the past month:");
            workouts.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
