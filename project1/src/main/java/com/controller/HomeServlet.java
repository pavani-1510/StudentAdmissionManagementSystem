package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("home.html");
            return;
        }

        switch (action) {
            case "adminLogin":
                response.sendRedirect("adminLogin.html");
                break;
            case "studentLogin":
                response.sendRedirect("login.html");
                break;
            case "studentSignup":
                response.sendRedirect("signup.html");
                break;
            default:
                response.sendRedirect("home.html");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
