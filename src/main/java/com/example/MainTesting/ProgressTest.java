package com.example.MainTesting;
//Shows weight entries over time for a user.

import com.example.dao.ProgressDAO;
import com.example.dao.ProgressDAOImpl;
import com.example.entity.Progress;

import java.util.List;

public class ProgressTest {
    public static void main(String[] args) {
        try {
            long testUserId = 1;

            ProgressDAO progressDAO = new ProgressDAOImpl();
            List<Progress> progressList = progressDAO.getWeightEntries(testUserId);

            System.out.println("Weight entries for user ID " + testUserId + ":");
            progressList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
