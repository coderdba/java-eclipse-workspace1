package com.dba.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.net.ssl.*;
import java.security.*;
import java.io.*;

public class LdapValidator {

	private String userId;
	private String[] ldapAttributeIDs = { "name", "distinguishedName", "sn", "givenname", "mail", "telephonenumber" };
	private Attributes ldapAttributes;

	public LdapValidator(String userId) throws Exception {

		this.userId = userId;
		this.ldapAttributes = getUserBasicAttributes(userId, getLdapContext());
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return ldapAttributes.get("name").toString().replace("name: ", "");
	}
	
	public String getEmail() {
		return ldapAttributes.get("mail").toString().replace("mail: ", "");
	}
	
	private LdapContext getLdapContext() {
		LdapContext ctx = null;
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			
			env.put(Context.SECURITY_PROTOCOL, "ssl");
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, "firstname.lastname");
			env.put(Context.SECURITY_CREDENTIALS, "userPassword");
			env.put(Context.PROVIDER_URL, "ldaps://ldapserver.company.com:636"); 
			// port 389/'636' or /3268/2001 usually

			ctx = new InitialLdapContext(env, null);
			System.out.println("Connection Successful.");
		} catch (NamingException nex) {
			System.out.println("LDAP Connection: FAILED");
			nex.printStackTrace();
		}
		return ctx;
	}

	private Attributes getUserBasicAttributes(String username, LdapContext ctx) throws Exception {

		Attributes attrs = null;

		try {

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// Set what has to be fetched as 'constraints'
			constraints.setReturningAttributes(ldapAttributeIDs);

			// Do the search
			NamingEnumeration answer = ctx.search(
					"OU=People,DC=shops,DC=company,DC=com", "sAMAccountName="+ username, constraints);
			
			if (answer.hasMore()) {
				attrs = ((SearchResult) answer.next()).getAttributes();

				System.out.println("name " + attrs.get("name"));
				System.out.println("distinguishedName "	+ attrs.get("distinguishedName"));
				System.out.println("givenname " + attrs.get("givenname"));
				System.out.println("sn " + attrs.get("sn"));
				System.out.println("mail " + attrs.get("mail"));
				System.out.println("telephonenumber " + attrs.get("telephonenumber"));
		
			} else {
				throw new Exception("Invalid User");
				}
			} catch (Exception ex) {
				//ex.printStackTrace();
				
				throw ex;
			}
		
		return attrs;
	}
}
