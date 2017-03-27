package com.dba.utils;

public class TestLdapValidator {

	public static void main(String[] args) {

		try {
		LdapValidator ldapValidator = new LdapValidator("ldapUserId");
		
		System.out.println ("User ldap details: " + ldapValidator.getUserId() + " " +
								ldapValidator.getEmail());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
