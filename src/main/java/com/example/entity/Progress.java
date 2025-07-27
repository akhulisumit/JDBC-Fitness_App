package com.example.entity;

import java.sql.Timestamp;

public class Progress {
    private long id;
    private long userId;
    private int weightKg;
    private Timestamp logTime;

    public Progress() {
    }

    public Progress(long id, long userId, int weightKg, Timestamp logTime) {
        this.id = id;
        this.userId = userId;
        this.weightKg = weightKg;
        this.logTime = logTime;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", userId=" + userId +
                ", weightKg=" + weightKg +
                ", logTime=" + logTime +
                '}';
    }
}
