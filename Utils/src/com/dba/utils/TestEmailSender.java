package com.dba.utils;

public class TestEmailSender {

	public static void main(String[] args) {

		try {
		EmailSender emailSender = new EmailSender();
		emailSender.sendEmail("firstname.lastname@company.com", "eclipse@laptop.com", 
				"testing from TestEmailSender.java", "Just testing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
