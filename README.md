# Fitness Tracker JDBC Project

A simple fitness tracker application demonstrating Java JDBC connectivity with a MySQL database, following best practices with Maven, DAO pattern, and modular code organization. This project supports user management, workout logging, exercise tracking, progress monitoring, and reporting.

---

## Features

- Manage users with their profile information
- Record workouts and associate exercises performed
- Track exercise details, sets, reps, duration, and calories burnt
- Log user weight progress over time
- Query workouts and exercises with useful reports:
  - List workouts by user in the past month
  - Retrieve exercises for a workout
  - Calculate weekly workout durations for the last 3 months
  - Show weight entries over time
  - Find the most frequently performed exercise type over a date range

---

## Project Structure

src/main/java/com/example/
├── DBConnection.java # Database connection utility
├── Main.java # Main app or test runner class
├── entity/ # Java classes representing database tables
│ ├── User.java
│ ├── Workout.java
│ ├── Exercise.java
│ ├── WorkoutExercise.java
│ └── Progress.java
└── dao/ # DAO interfaces and JDBC implementations
├── UserDAO.java & UserDAOImpl.java
├── WorkoutDAO.java & WorkoutDAOImpl.java
├── ExerciseDAO.java & ExerciseDAOImpl.java
├── WorkoutExerciseDAO.java & WorkoutExerciseDAOImpl.java
└── ProgressDAO.java & ProgressDAOImpl.java

text

---

## Prerequisites

- Java Development Kit (JDK) 8 or above installed
- Apache Maven 3.6 or newer installed
- MySQL server installed and running
- MySQL database and schema created with tables matching your entities
- MySQL user credentials (username/password) with appropriate permissions

---

## Setup Instructions

1. **Clone the repository**

git clone https://github.com/yourusername/fitness-tracker-jdbc.git
cd fitness-tracker-jdbc

text

2. **Configure database connection**

Edit the file `src/main/java/com/example/DBConnection.java` and update the following constants:

private static final String URL = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";

text

3. **Build the project with Maven**

This downloads dependencies and compiles the code:

mvn clean package

text

4. **Run the application**

You can run the packaged executable jar:

java -jar target/fitness_tracker-1.0-jar-with-dependencies.jar

text

Or run specific test/main classes via Maven exec plugin:

mvn compile exec:java -Dexec.mainClass="com.example.Main" # Replace Main with your main class

text

---

## Usage

- The `Main` class contains demo/testing code for adding users, workouts, progress entries, and fetching reports.
- Modify or create your own main classes or CLI to extend functionality.
- Expand entity and DAO classes as needed for more features.
- For database setup, include your schema creation SQL script in the project root or docs.
