package com.example.dao;

import com.example.DBConnection;
import com.example.entity.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAOImpl implements ExerciseDAO {

    @Override
    public List<Exercise> getExercisesForWorkout(long workoutId) throws SQLException {
        String sql = "SELECT e.id, e.name, e.type, e.`desc` " +
                "FROM exercises e " +
                "JOIN workout_exercise we ON e.id = we.exercise_id " +
                "WHERE we.workout_id = ?";
        List<Exercise> exercises = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, workoutId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Exercise ex = new Exercise(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("desc"));
                    exercises.add(ex);
                }
            }
        }
        return exercises;
    }

    @Override
    public void addExercise(Exercise exercise) throws SQLException {
        String sql = "INSERT INTO exercises (name, type, `desc`) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exercise.getName());
            stmt.setString(2, exercise.getType());
            stmt.setString(3, exercise.getDesc());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    exercise.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public Exercise getExerciseById(long id) throws SQLException {
        String sql = "SELECT id, name, type, `desc` FROM exercises WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Exercise(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getString("desc"));
                }
            }
        }
        return null;
    }

    @Override
    public List<Exercise> getAllExercises() throws SQLException {
        String sql = "SELECT id, name, type, `desc` FROM exercises";
        List<Exercise> exercises = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Exercise ex = new Exercise(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("desc"));
                exercises.add(ex);
            }
        }
        return exercises;
    }

    @Override
    public void updateExercise(Exercise exercise) throws SQLException {
        String sql = "UPDATE exercises SET name = ?, type = ?, `desc` = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, exercise.getName());
            stmt.setString(2, exercise.getType());
            stmt.setString(3, exercise.getDesc());
            stmt.setLong(4, exercise.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteExercise(long id) throws SQLException {
        String sql = "DELETE FROM exercises WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public String getMostFrequentExerciseType(long userId, java.sql.Date from, java.sql.Date to) throws SQLException {
        String sql = "SELECT e.type, COUNT(*) AS freq " +
                "FROM workouts w " +
                "JOIN workout_exercise we ON w.id = we.workout_id " +
                "JOIN exercises e ON we.exercise_id = e.id " +
                "WHERE w.user_id = ? AND w.date BETWEEN ? AND ? " +
                "GROUP BY e.type " +
                "ORDER BY freq DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setDate(2, from);
            stmt.setDate(3, to);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("type");
                }
            }
        }
        return null;
    }
}
