package com.dba.ora.um;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import com.dba.utils.*;
import com.dba.ora.dh.*;
import com.dba.ora.dh.DhOracle;

@WebServlet(description = "Oracle User management functions", urlPatterns = { "/OraUserManager" })
public class OraUserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String lanId 			;
	private String displayName 		;
	private String lanPassword 		;
	private String dbUserId			;
	private String dbServiceOrSID 	;
	private String dbScanOrHost   	;
	private String port			  	;
	private String[] actions		;
	private String dbNewPw			;
	private StringBuffer completedActions   ;
       
    public OraUserManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		lanId 			= request.getParameter("lanId");
		displayName 	= request.getParameter("displayName");
		lanPassword 	= request.getParameter("lanPassword");
		dbUserId		= request.getParameter("dbUserId");
		dbServiceOrSID 	= request.getParameter("dbServiceOrSID");
		dbScanOrHost   	= request.getParameter("dbScanOrHost");
		port			= request.getParameter("port");
		actions			= request.getParameterValues("action");
		dbNewPw			= request.getParameter("dbNewPw");
		completedActions = new StringBuffer();
		
		// Validate the user
		try {
			validateUserInLDAP();
		}
		catch (Exception e) {
			
			showLDAPErrorPage(response);
			throw new ServletException("Invalid User");
		}
		
		// Perform user requested actions
		try {
				performUserActions();
				
				// Show successful completion page
				showDBSuccessPage(response);
		}
		catch (Exception e) {
			showDBErrorPage(response, e);
			throw new ServletException("Unsuccessful database action");
		}
	}
	
	private void validateUserInLDAP() throws Exception {
		
		try {
				LdapSelfValidator userLdapSettings = new LdapSelfValidator (lanId, displayName, lanPassword);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	private void showLDAPErrorPage(HttpServletResponse response){
		
		try {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("ERROR - <br>" + "User: " + displayName + " could not be verfied <br>" +
				 "Check your LanID, Password and Display Name provided <br>" +
				 "If they are correct and you still see this error, contact your DBA for help <br>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	private void performUserActions()  throws Exception {
		
		String sqlStatement = "";
		
		// Iterate over actions chosen
		for (String action : actions) {
			
			if (action.equals("unlock")) {
				completedActions.append("Unlock user started - if success, will show completion message below<br>");
				sqlStatement = "alter user " + dbUserId + " account unlock";
			}
			else if (action.equals("pwReset")) {
				completedActions.append("User password reset started - if success, will show completion message below<br>");
				sqlStatement = "alter user " + dbUserId + " identified by " + dbNewPw;
			}
			try {
			       DhOracle dhOracle = new DhOracle();
			       //dhOracle.executeQuery ("sys", "syspassword", dbScanOrHost, port, 
			       dhOracle.executeQuery ("gm2", "a$b$123", dbScanOrHost, port,
			    		   					dbServiceOrSID, sqlStatement);
					if (action.equals("unlock")) {
						completedActions.append("Unlock user completed <br><br>");
					}
					else if (action.equals("pwReset")) {
						completedActions.append("User password reset completed <br><br>");
					}
			}
			catch (Exception e) {
				throw e;
			}
		}
	}
	
	private void showDBErrorPage(HttpServletResponse response,  Exception exin){
		
		try {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("ERROR - Could not complete actions for DB User: " + dbUserId + "<br><br>");
		out.println("Completed actions: <br>");
		out.println(completedActions.toString());
		
		exin.printStackTrace();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showDBSuccessPage(HttpServletResponse response) {
		
		try {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Completed actions: <br>");
		out.println(completedActions.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
