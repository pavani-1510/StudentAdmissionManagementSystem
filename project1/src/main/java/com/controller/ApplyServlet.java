package com.controller;

import com.model.Application;
import com.model.ApplicationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ApplyServlet")
public class ApplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward to the form page initially
        RequestDispatcher rd = request.getRequestDispatcher("applicationform.html");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // Get form parameters
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String mobileNumber = request.getParameter("mobileNumber");
        String emailId = request.getParameter("emailId");
        String gender = request.getParameter("gender");
        String branch = request.getParameter("branch");
        String twelfthMarkSheetLink = request.getParameter("twelfthMarkSheetLink");
        String status = "pending";

        // Create Application object
        Application application = new Application(username, fullName, mobileNumber, emailId, 
                                                 gender, twelfthMarkSheetLink, branch, status);

        // Validate required fields
        String errorMessage = null;
        if (username == null || username.trim().isEmpty()) {
            errorMessage = "Username is required.";
        }

        if (errorMessage != null) {
            // Forward to the form page with an error message
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("applicationform.html");
            rd.include(request, response);
        } else {
            ApplicationManager appManager = new ApplicationManager();
            try {
                String result = appManager.applyApplication(application);
                // Set success message and include the form page again
                request.setAttribute("successMessage", "Form successfully submitted!");
                request.getRequestDispatcher("application.html").forward(request, response);
            } catch (Exception e) {
                pw.print("An error occurred: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("applicationform.html");
                rd.include(request, response);
            } finally {
                pw.close();
            }
        }
    }

}
