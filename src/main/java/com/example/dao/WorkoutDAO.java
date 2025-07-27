package com.example.dao;

import com.example.entity.Workout;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface WorkoutDAO {
    // Get all workouts for a given user in the past month
    List<Workout> getWorkoutsForUserPastMonth(long userId) throws SQLException;

    // Calculate total workout durations per week for last 3 months
    Map<String, Integer> getWeeklyDurations(long userId) throws SQLException;
}
