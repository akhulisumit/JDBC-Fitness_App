package com.example.dao;

import com.example.entity.Exercise;
import java.sql.SQLException;
import java.util.List;

public interface ExerciseDAO {
    List<Exercise> getExercisesForWorkout(long workoutId) throws SQLException;

    // CRUD methods if you want to add them:
    void addExercise(Exercise exercise) throws SQLException;

    Exercise getExerciseById(long id) throws SQLException;

    List<Exercise> getAllExercises() throws SQLException;

    void updateExercise(Exercise exercise) throws SQLException;

    void deleteExercise(long id) throws SQLException;

    // For the advanced function:
    String getMostFrequentExerciseType(long userId, java.sql.Date from, java.sql.Date to) throws SQLException;
}
