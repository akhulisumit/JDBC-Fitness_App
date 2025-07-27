package com.example.resources.MainTesting;
//Adds a progress entry and lists all progress for a user.

import com.example.dao.ProgressDAO;
import com.example.dao.ProgressDAOImpl;
import com.example.entity.Progress;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class ProgressAddAndRetrieveTest {
    public static void main(String[] args) {
        try {
            ProgressDAO progressDAO = new ProgressDAOImpl();

            // Add a new progress entry
            Progress progress = new Progress();
            progress.setUserId(1); // Change to valid user ID
            progress.setWeightKg(70);
            progress.setLogTime(Timestamp.from(Instant.now()));

            progressDAO.addProgress(progress);
            System.out.println("Added progress entry: " + progress);

            // Fetch all progress entries for that user
            List<Progress> allProgress = progressDAO.getWeightEntries(1);
            System.out.println("All progress entries:");
            allProgress.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
