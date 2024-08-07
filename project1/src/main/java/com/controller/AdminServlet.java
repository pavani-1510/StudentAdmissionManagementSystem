package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.StudentManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
	    String username = request.getParameter("t1");
	    String password = request.getParameter("t2");
	    try {
	        if ("admin".equals(username) && "admin123".equals(password)) {
	            RequestDispatcher rd = request.getRequestDispatcher("admin.html");
	            rd.forward(request, response);
	        } else {
	            pw.print("Invalid username or password.");
	            RequestDispatcher rd = request.getRequestDispatcher("adminLogin.html");
	            rd.include(request, response);
	        }
	    } catch (Exception e) {
	        pw.print(e.getMessage());
	    }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
