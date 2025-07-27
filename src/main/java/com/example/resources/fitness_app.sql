-- fitness_tracker_schema_and_data.sql

-- 1. Create Database and Use It
CREATE DATABASE IF NOT EXISTS fitness_tracker_db;
USE fitness_tracker_db;

-- 2. Table Definitions

-- Users Table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    age INT,
    phone VARCHAR(20),
    height INT,
    gender VARCHAR(10)
);

-- Exercises Table
CREATE TABLE exercises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    `desc` TEXT
);

-- Workouts Table
CREATE TABLE workouts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    start_time TIME,
    end_time TIME,
    date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Workout_Exercise Table (joining workouts and exercises)
CREATE TABLE workout_exercise (
    workout_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    sets INT,
    reps INT,
    duration TIME,
    calories_burnt INT,
    PRIMARY KEY (workout_id, exercise_id),
    FOREIGN KEY (workout_id) REFERENCES workouts(id) ON DELETE CASCADE,
    FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE
);

-- Progress Table (user weight logs)
CREATE TABLE progress (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    weight_kg INT NOT NULL,
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 3. Sample Demo Data Inserts

-- Users
INSERT INTO users (email, password, name, age, phone, height, gender) VALUES
('alice@example.com', 'password1', 'Alice Smith', 30, '1234567890', 165, 'Female'),
('bob@example.com', 'password2', 'Bob Johnson', 28, '0987654321', 180, 'Male');

-- Exercises
INSERT INTO exercises (name, type, `desc`) VALUES
('Running', 'Cardio', 'Outdoor running exercise'),
('Push Ups', 'Strength', 'Upper body push exercise'),
('Cycling', 'Cardio', 'Stationary bike cycling'),
('Squats', 'Strength', 'Lower body squat exercise');

-- Workouts
INSERT INTO workouts (user_id, start_time, end_time, date) VALUES
(1, '06:30:00', '07:15:00', '2025-07-01'),
(1, '18:00:00', '19:00:00', '2025-07-10'),
(2, '07:00:00', '07:45:00', '2025-07-05');

-- Workout_Exercise linking exercises to workouts
INSERT INTO workout_exercise (workout_id, exercise_id, sets, reps, duration, calories_burnt) VALUES
(1, 1, NULL, NULL, '00:45:00', 400),
(1, 2, 3, 15, NULL, 150),
(2, 3, NULL, NULL, '01:00:00', 500),
(3, 4, 4, 12, NULL, 200);

-- Progress (weight logs)
INSERT INTO progress (user_id, weight_kg, log_time) VALUES
(1, 65, '2025-06-01 08:00:00'),
(1, 66, '2025-06-15 08:00:00'),
(1, 67, '2025-07-01 08:00:00'),
(2, 80, '2025-06-05 09:30:00'),
(2, 78, '2025-07-01 09:30:00');
