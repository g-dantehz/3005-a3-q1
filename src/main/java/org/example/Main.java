package org.example;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    static String url = "jdbc:postgresql://localhost:5432/3005_a3";
    static String user = "postgres";
    static String password = "your_password_here";

    public static void getAllStudents() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students ORDER BY student_id");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                System.out.print(resultSet.getString("student_id") + " ");
                System.out.print(resultSet.getString("first_name") + " ");
                System.out.print(resultSet.getString("last_name") + " ");
                System.out.print(resultSet.getString("email") + " ");
                System.out.println(resultSet.getString("enrollment_date"));
            }

            // release database resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addStudent(String firstName, String lastName,
                                  String email, String enrollmentDate) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO students (first_name, last_name, email, enrollment_date) "
                            + "VALUES (?, ?, ?, ?)");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setDate(4, Date.valueOf(enrollmentDate));
            ps.executeUpdate();
            System.out.println("Added student " + firstName + " " + lastName + ".");

            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Updates the email address for a student with the specified student_id.
    public static void updateStudentEmail(int studentId, String newEmail) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE students SET email = ? WHERE student_id = ?");
            ps.setString(1, newEmail);
            ps.setInt(2, studentId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated email for student " + studentId + ".");
            } else {
                System.out.println("No student found with id " + studentId + ".");
            }

            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Deletes the record of the student with the specified student_id.
    public static void deleteStudent(int studentId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM students WHERE student_id = ?");
            ps.setInt(1, studentId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Deleted student " + studentId + ".");
            } else {
                System.out.println("No student found with id " + studentId + ".");
            }

            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** Runs each CRUD function in order and prints the table after each step. */
    public static void main(String[] args) {
        System.out.println("----------");
        System.out.println("Initial students: ");
        System.out.println("----------");
        getAllStudents();

        System.out.println("----------");
        System.out.println("INSERT: Adding a student");
        System.out.println("----------");
        addStudent("Alice", "Johnson", "alice.johnson@example.com", "2023-09-03");
        getAllStudents();

        System.out.println("----------");
        System.out.println("UPDATE: Updating John Doe's email");
        System.out.println("----------");
        updateStudentEmail(1, "cooler_john.doe@example.com");
        getAllStudents();

        System.out.println("----------");
        System.out.println("DELETE: Delete Jim Beam");
        System.out.println("----------");
        deleteStudent(3);
        getAllStudents();
    }
}

