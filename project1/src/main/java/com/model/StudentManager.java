package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project_ep";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Pavani@595";

    private Connection connection;

    public StudentManager() {
        try {
            // Initialize the database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    

    public boolean validateUser(String sname, String password) {
        String sql = "SELECT password FROM Student WHERE sname = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sname);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return password.equals(storedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String insertData(Student s1) {
        String sql = "INSERT INTO Student (sname, number, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, s1.getSname());
            pstmt.setString(2, s1.getNumber());
            pstmt.setString(3, s1.getPassword());
            pstmt.executeUpdate();
            return "Signup done successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Signup failed: " + e.getMessage();
        }
    }

    public void close() {
        // Close the database connection
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
