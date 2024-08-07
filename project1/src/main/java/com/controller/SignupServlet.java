package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Student;
import com.model.StudentManager;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try {
            // Retrieve form data
            String sname = request.getParameter("t1");
            String mobile = request.getParameter("t2");
            String password = request.getParameter("t3");
            String confirmPassword = request.getParameter("t4");

            // Check if passwords match
            if (password.equals(confirmPassword)) {
                // Create Student object with provided data
                Student s1 = new Student(sname, mobile, password);

                // Insert data into the database
                StudentManager sm = new StudentManager();
                String ack = sm.insertData(s1);

                if ("success".equals(ack)) {  // Ensure 'success' is the exact string returned
                    // Redirect to the dashboard page
                    response.sendRedirect("dashboard.html");
                } else {
                    // Output acknowledgment
                    pw.print("<html><body><p style='color:red;'>Signup failed: " + ack + "</p></body></html>");
                }
            } else {
                // Passwords do not match
                pw.print("<html><body><p style='color:red;'>Password and Confirm Password do not match. Please try again.</p></body></html>");
                // Optionally redirect back to signup page
                // response.sendRedirect("signup.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<html><body><p style='color:red;'>An error occurred: " + e.getMessage() + "</p></body></html>");
        } finally {
            pw.close();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests by redirecting to the signup page
        response.sendRedirect("signup.html");
    }
}
