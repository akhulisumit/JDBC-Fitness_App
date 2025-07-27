package com.example.dao;

import com.example.entity.Progress;
import java.sql.SQLException;
import java.util.List;

public interface ProgressDAO {
    List<Progress> getWeightEntries(long userId) throws SQLException;

    // Typical CRUD methods (optional, but useful)
    void addProgress(Progress progress) throws SQLException;

    Progress getProgressById(long id) throws SQLException;

    void updateProgress(Progress progress) throws SQLException;

    void deleteProgress(long id) throws SQLException;
}
