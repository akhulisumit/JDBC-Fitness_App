package com.example.entity;

import java.sql.Time;

public class WorkoutExercise {
    private long workoutId;
    private long exerciseId;
    private Integer sets;
    private Integer reps;
    private Time duration;
    private Integer caloriesBurnt;

    public WorkoutExercise() {
    }

    public WorkoutExercise(long workoutId, long exerciseId, Integer sets, Integer reps, Time duration,
            Integer caloriesBurnt) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
    }

    // Getters and setters

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Integer getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(Integer caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" +
                "workoutId=" + workoutId +
                ", exerciseId=" + exerciseId +
                ", sets=" + sets +
                ", reps=" + reps +
                ", duration=" + duration +
                ", caloriesBurnt=" + caloriesBurnt +
                '}';
    }
}
