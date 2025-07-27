package com.example.dao;

import com.example.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws SQLException;

    User getUserById(long id) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(long id) throws SQLException;
}
