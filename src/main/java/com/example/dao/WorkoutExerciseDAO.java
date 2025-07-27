package com.example.dao;

import com.example.entity.WorkoutExercise;
import java.sql.SQLException;
import java.util.List;

public interface WorkoutExerciseDAO {
    List<WorkoutExercise> getExercisesForWorkout(long workoutId) throws SQLException;

    // You can add CRUD methods as needed:
    void addWorkoutExercise(WorkoutExercise we) throws SQLException;

    void deleteWorkoutExercise(long workoutId, long exerciseId) throws SQLException;
}
