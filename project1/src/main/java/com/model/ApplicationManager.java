package com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project_ep";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Pavani@595";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC Driver not found");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public List<Application> getAllApplications() throws Exception {
        String sql = "SELECT * FROM applications";
        List<Application> applications = new ArrayList<>();
        
        try (Connection conn = getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Application app = new Application(
                    rs.getString("username"),
                    rs.getString("fullName"),
                    rs.getString("mobileNumber"),
                    rs.getString("emailId"),
                    rs.getString("gender"),
                    rs.getString("twelfthMarkSheetLink"),
                    rs.getString("branch"),
                    rs.getString("status")
                );
                applications.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while retrieving applications: " + e.getMessage(), e);
        }
        return applications;
    }

    public void updateApplicationStatus(String username, String status) throws Exception {
        String sql = "UPDATE applications SET status = ? WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Application not found or status unchanged for username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while updating application status: " + e.getMessage(), e);
        }
    }

    public String applyApplication(Application application) throws Exception {
        String sql = "INSERT INTO applications (username, fullName, mobileNumber, emailId, gender, twelfthMarkSheetLink, branch, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, application.getUsername());
            pstmt.setString(2, application.getFullName());
            pstmt.setString(3, application.getMobileNumber());
            pstmt.setString(4, application.getEmailId());
            pstmt.setString(5, application.getGender());
            pstmt.setString(6, application.getTwelfthMarkSheetLink());
            pstmt.setString(7, application.getBranch());
            pstmt.setString(8, application.getStatus());
            pstmt.executeUpdate();
            return "Application submitted successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while applying the application: " + e.getMessage(), e);
        }
    }

    public String getApplicationStatus(String username) throws Exception {
        String sql = "SELECT status FROM applications WHERE username = ?";
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                } else {
                    throw new Exception("Application not found for username: " + username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error while retrieving application status: " + e.getMessage(), e);
        }
    }

    public Application getApplicationByUsername(String username) throws Exception {
        String sql = "SELECT * FROM applications WHERE username = ?";
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Application(
                        rs.getString("username"),
                        rs.getString("fullName"),
                        rs.getString("mobileNumber"),
                        rs.getString("emailId"),
                        rs.getString("gender"),
                        rs.getString("twelfthMarkSheetLink"),
                        rs.getString("branch"),
                        rs.getString("status")
                    );
                } else {
                    throw new Exception("Application not found for username: " + username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while retrieving application: " + e.getMessage(), e);
        }
    }
}
