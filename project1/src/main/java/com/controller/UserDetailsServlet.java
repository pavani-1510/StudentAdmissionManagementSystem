package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String username = (String) request.getSession().getAttribute("username");
        String userId = (String) request.getSession().getAttribute("userId");

        // Check if userId is retrieved correctly
        if (userId == null) {
            userId = "Not Available"; // Handle null case
        }

        // Create a user details object
        UserDetails userDetails = new UserDetails(username, userId);

        // Convert the user details to JSON manually
        String json = toJson(userDetails);

        // Write JSON response
        response.getWriter().write(json);
    }

    private static class UserDetails {
        private String username;
        private String userId;

        public UserDetails(String username, String userId) {
            this.username = username;
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public String getUserId() {
            return userId;
        }
    }

    // Method to manually convert UserDetails to JSON
    private String toJson(UserDetails userDetails) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"username\":\"").append(userDetails.getUsername()).append("\",");
        json.append("\"userId\":\"").append(userDetails.getUserId()).append("\"");
        json.append("}");
        return json.toString();
    }
}
