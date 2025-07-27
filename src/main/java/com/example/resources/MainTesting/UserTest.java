package com.example.resources.MainTesting;
//Tests User creation and listing users.

import com.example.entity.User;
import com.example.dao.UserDAO;
import com.example.dao.UserDAOImpl;

public class UserTest {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            User user = new User(0, "test@example.com", "password", "Test User", 25, "1234567890", 170, "Male");
            userDAO.addUser(user);
            System.out.println("User added: " + user);

            System.out.println("All users:");
            userDAO.getAllUsers().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
