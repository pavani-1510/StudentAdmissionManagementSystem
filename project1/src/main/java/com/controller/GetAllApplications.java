package com.controller;

import java.io.IOException;
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

@WebServlet("/GetAllApplications")
public class GetAllApplications extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project_ep";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Pavani@595";
    private static final String SELECT_QUERY = "SELECT * FROM applications";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            StringBuilder html = new StringBuilder();
            html.append("<html><head>")
                .append("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>")
                .append("</head><body class='bg-dark text-white'>")
                .append("<div class='container mt-5'>")
                .append("<h2 class='text-center'>All Applications</h2>")
                .append("<table class='table table-striped table-dark mt-4'>")
                .append("<thead><tr>")
                .append("<th>Username</th><th>Full Name</th><th>Mobile Number</th>")
                .append("<th>Email ID</th><th>Gender</th><th>12th Mark Sheet Link</th>")
                .append("<th>Branch</th><th>Status</th>")
                .append("</tr></thead>")
                .append("<tbody>");

            while (resultSet.next()) {
                html.append("<tr>")
                    .append("<td>").append(resultSet.getString("username")).append("</td>")
                    .append("<td>").append(resultSet.getString("fullName")).append("</td>")
                    .append("<td>").append(resultSet.getString("mobileNumber")).append("</td>")
                    .append("<td>").append(resultSet.getString("emailId")).append("</td>")
                    .append("<td>").append(resultSet.getString("gender")).append("</td>")
                    .append("<td><a href='").append(resultSet.getString("twelfthMarkSheetLink")).append("' target='_blank'>View</a></td>")
                    .append("<td>").append(resultSet.getString("branch")).append("</td>")
                    .append("<td>").append(resultSet.getString("status")).append("</td>")
                    .append("</tr>");
            }

            html.append("</tbody></table>");
            html.append("</div></body></html>");
            response.getWriter().write(html.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("<p class='text-danger text-center'>An error occurred while retrieving the application details.</p>");
        }
    }
}
