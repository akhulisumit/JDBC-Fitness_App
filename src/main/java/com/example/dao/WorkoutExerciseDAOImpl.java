package com.example.dao;

import com.example.DBConnection;
import com.example.entity.WorkoutExercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutExerciseDAOImpl implements WorkoutExerciseDAO {

    @Override
    public List<WorkoutExercise> getExercisesForWorkout(long workoutId) throws SQLException {
        String sql = "SELECT workout_id, exercise_id, sets, reps, duration, calories_burnt " +
                "FROM workout_exercise WHERE workout_id = ?";
        List<WorkoutExercise> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, workoutId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WorkoutExercise we = new WorkoutExercise(
                            rs.getLong("workout_id"),
                            rs.getLong("exercise_id"),
                            (Integer) rs.getObject("sets"),
                            (Integer) rs.getObject("reps"),
                            rs.getTime("duration"),
                            (Integer) rs.getObject("calories_burnt"));
                    result.add(we);
                }
            }
        }
        return result;
    }

    @Override
    public void addWorkoutExercise(WorkoutExercise we) throws SQLException {
        String sql = "INSERT INTO workout_exercise (workout_id, exercise_id, sets, reps, duration, calories_burnt) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, we.getWorkoutId());
            stmt.setLong(2, we.getExerciseId());
            if (we.getSets() != null)
                stmt.setInt(3, we.getSets());
            else
                stmt.setNull(3, Types.INTEGER);
            if (we.getReps() != null)
                stmt.setInt(4, we.getReps());
            else
                stmt.setNull(4, Types.INTEGER);
            stmt.setTime(5, we.getDuration());
            if (we.getCaloriesBurnt() != null)
                stmt.setInt(6, we.getCaloriesBurnt());
            else
                stmt.setNull(6, Types.INTEGER);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteWorkoutExercise(long workoutId, long exerciseId) throws SQLException {
        String sql = "DELETE FROM workout_exercise WHERE workout_id = ? AND exercise_id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, workoutId);
            stmt.setLong(2, exerciseId);
            stmt.executeUpdate();
        }
    }
}
