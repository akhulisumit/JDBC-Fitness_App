package com.example.resources.MainTesting;
//Finds the most frequently performed exercise type within a period.

import com.example.dao.ExerciseDAO;
import com.example.dao.ExerciseDAOImpl;

import java.sql.Date;
import java.time.LocalDate;

public class MostFrequentExerciseTypeTest {
    public static void main(String[] args) {
        try {
            long testUserId = 1;
            LocalDate today = LocalDate.now();
            Date toDate = Date.valueOf(today);
            Date fromDate = Date.valueOf(today.minusMonths(2));

            ExerciseDAO exerciseDAO = new ExerciseDAOImpl();
            String mostFrequentType = exerciseDAO.getMostFrequentExerciseType(testUserId, fromDate, toDate);

            if (mostFrequentType != null) {
                System.out.println("Most frequent exercise type for user " + testUserId +
                        " from " + fromDate + " to " + toDate + ": " + mostFrequentType);
            } else {
                System.out.println("No exercises found for user in this period.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
