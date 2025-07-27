package com.example.resources.MainTesting;
//Retrieves exercises for a specific workout.

import com.example.dao.ExerciseDAO;
import com.example.dao.ExerciseDAOImpl;
import com.example.entity.Exercise;

import java.util.List;

public class ExerciseTest {
    public static void main(String[] args) {
        try {
            long testWorkoutId = 1; // Replace with actual workout ID

            ExerciseDAO exerciseDAO = new ExerciseDAOImpl();
            List<Exercise> exercises = exerciseDAO.getExercisesForWorkout(testWorkoutId);

            System.out.println("Exercises for workout ID " + testWorkoutId + ":");
            exercises.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
