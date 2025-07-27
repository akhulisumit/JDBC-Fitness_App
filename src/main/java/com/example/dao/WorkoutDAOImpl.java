package com.example.dao;

import com.example.DBConnection;
import com.example.entity.Workout;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkoutDAOImpl implements WorkoutDAO {

    @Override
    public List<Workout> getWorkoutsForUserPastMonth(long userId) throws SQLException {
        String sql = "SELECT id, user_id, start_time, end_time, date FROM workouts " +
                "WHERE user_id = ? AND date >= CURDATE() - INTERVAL 1 MONTH ORDER BY date DESC";

        List<Workout> workouts = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Workout workout = new Workout();
                    workout.setId(rs.getLong("id"));
                    workout.setUserId(rs.getLong("user_id"));
                    workout.setStartTime(rs.getTime("start_time"));
                    workout.setEndTime(rs.getTime("end_time"));
                    workout.setDate(rs.getDate("date"));
                    workouts.add(workout);
                }
            }
        }
        return workouts;
    }

    @Override
    public Map<String, Integer> getWeeklyDurations(long userId) throws SQLException {
        String sql = "SELECT YEARWEEK(date, 1) AS yw, " +
                "SUM(TIMESTAMPDIFF(MINUTE, start_time, end_time)) AS total_minutes " +
                "FROM workouts " +
                "WHERE user_id = ? AND date >= CURDATE() - INTERVAL 3 MONTH " +
                "GROUP BY yw ORDER BY yw";

        Map<String, Integer> weekToMinutes = new LinkedHashMap<>();

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String yearWeek = rs.getString("yw"); // e.g., "202528"
                    int totalMinutes = rs.getInt("total_minutes");
                    weekToMinutes.put(yearWeek, totalMinutes);
                }
            }
        }
        return weekToMinutes;
    }
}
