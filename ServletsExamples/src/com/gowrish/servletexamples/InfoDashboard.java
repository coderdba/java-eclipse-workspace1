package com.gm.servletexamples;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/InfoDashboard")
public class InfoDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InfoDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int refreshIntervalSecs = 10;
		
		// Set refresh, autoload time in seconds
		response.setIntHeader("Refresh", refreshIntervalSecs);

		// Set response content type
		response.setContentType("text/html");

		// Get current time
		Calendar calendar = new GregorianCalendar();
		String am_pm;
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";

		String CT = hour + ":" + minute + ":" + second + " " + am_pm;

		PrintWriter out = response.getWriter();
		String title = "Info Dashboard";
		String docType = "<!doctype html " // html 5 does not need more info for doctype
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + 
				"<head><title>" + title + "</title></head>\n" + 
				"<body bgcolor=\"#f0f0f0\">\n" +
				"<h1 align=\"center\">" + title + "</h1>\n" +
				"<br><br>" + "<align=\"center\">" + "(refreshes every " + refreshIntervalSecs + " seconds)" + 
				"<br><br>" +
				"<p>Current Time is: " + CT + "</p>\n");

	}

}
