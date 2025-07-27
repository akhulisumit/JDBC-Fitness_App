package com.example.MainTesting;
//Calculates total workout duration per week for last 3 months.

import com.example.dao.WorkoutDAO;
import com.example.dao.WorkoutDAOImpl;

import java.util.Map;

public class WorkoutDurationTest {
    public static void main(String[] args) {
        try {
            long testUserId = 1;

            WorkoutDAO workoutDAO = new WorkoutDAOImpl();
            Map<String, Integer> durations = workoutDAO.getWeeklyDurations(testUserId);

            System.out.println("Weekly workout durations (minutes) in last 3 months:");
            durations.forEach((week, mins) -> System.out.println(week + ": " + mins + " minutes"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
