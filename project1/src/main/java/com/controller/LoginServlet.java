package com.controller; // Change this to your package name

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.StudentManager;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("t1");
        String password = request.getParameter("t2");

        // Use StudentManager to validate user credentials
        StudentManager studentManager = new StudentManager();
        boolean isValidUser = studentManager.validateUser(username, password);

        if (isValidUser) {
            response.sendRedirect("dashboard.html"); // Redirect to dashboard
        } else {
            response.sendRedirect("login.html?error=true"); // Redirect back to login page with error
        }
    }
}
