package com.controller;

import com.google.gson.Gson;
import com.model.Application;
import com.model.ApplicationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TrackServlet")
public class TrackServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    if (username == null || username.trim().isEmpty()) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"error\": \"Username is required.\"}");
	        return;
	    }

	    ApplicationManager appManager = new ApplicationManager();
	    try {
	        Application application = appManager.getApplicationByUsername(username);
	        if (application != null) {
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write(new Gson().toJson(application));
	        } else {
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write("{\"error\": \"No application found for the given username.\"}");
	        }
	    } catch (Exception e) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"error\": \"Error retrieving application details: " + e.getMessage() + "\"}");
	    }
	}

}
