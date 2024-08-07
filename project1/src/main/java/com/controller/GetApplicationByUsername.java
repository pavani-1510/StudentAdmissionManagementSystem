package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetApplicationByUsername")
public class GetApplicationByUsername extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project_ep";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Pavani@595";
    private static final String SELECT_QUERY = "SELECT * FROM applications WHERE username = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Username is required.\"}");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String applicationJson = String.format(
                        "{\"username\":\"%s\",\"fullName\":\"%s\",\"mobileNumber\":\"%s\",\"emailId\":\"%s\",\"gender\":\"%s\",\"twelfthMarkSheetLink\":\"%s\",\"branch\":\"%s\",\"status\":\"%s\"}",
                        resultSet.getString("username"),
                        resultSet.getString("fullName"),
                        resultSet.getString("mobileNumber"),
                        resultSet.getString("emailId"),
                        resultSet.getString("gender"),
                        resultSet.getString("twelfthMarkSheetLink"),
                        resultSet.getString("branch"),
                        resultSet.getString("status")
                    );
                    response.getWriter().write(applicationJson);
                } else {
                    response.getWriter().write("{\"error\":\"No application found for the given username.\"}");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"An error occurred while retrieving the application details.\"}");
        }
    }
}
