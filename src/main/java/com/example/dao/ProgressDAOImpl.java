package com.example.dao;

import com.example.DBConnection;
import com.example.entity.Progress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressDAOImpl implements ProgressDAO {

    @Override
    public List<Progress> getWeightEntries(long userId) throws SQLException {
        String sql = "SELECT id, user_id, weight_kg, log_time FROM progress WHERE user_id = ? ORDER BY log_time";
        List<Progress> entries = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Progress progress = new Progress(
                            rs.getLong("id"),
                            rs.getLong("user_id"),
                            rs.getInt("weight_kg"),
                            rs.getTimestamp("log_time"));
                    entries.add(progress);
                }
            }
        }
        return entries;
    }

    @Override
    public void addProgress(Progress progress) throws SQLException {
        String sql = "INSERT INTO progress (user_id, weight_kg, log_time) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, progress.getUserId());
            stmt.setInt(2, progress.getWeightKg());
            stmt.setTimestamp(3, progress.getLogTime());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next())
                    progress.setId(rs.getLong(1));
            }
        }
    }

    @Override
    public Progress getProgressById(long id) throws SQLException {
        String sql = "SELECT id, user_id, weight_kg, log_time FROM progress WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Progress(
                            rs.getLong("id"),
                            rs.getLong("user_id"),
                            rs.getInt("weight_kg"),
                            rs.getTimestamp("log_time"));
                }
            }
        }
        return null;
    }

    @Override
    public void updateProgress(Progress progress) throws SQLException {
        String sql = "UPDATE progress SET user_id = ?, weight_kg = ?, log_time = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, progress.getUserId());
            stmt.setInt(2, progress.getWeightKg());
            stmt.setTimestamp(3, progress.getLogTime());
            stmt.setLong(4, progress.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteProgress(long id) throws SQLException {
        String sql = "DELETE FROM progress WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
