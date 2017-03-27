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

public class LdapQuery {
	
	public static void main(String[] args) {
		LdapQuery retrieveUserAttributes = new LdapQuery();
		
    	retrieveUserAttributes.getUserBasicAttributes("user",    													retrieveUserAttributes.getLdapContext());
	}

	public LdapContext getLdapContext(){
		LdapContext ctx = null;
		try{
			Hashtable <String, String> env = new Hashtable <String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, "Firstname.Lastname");
			env.put(Context.SECURITY_CREDENTIALS, "userPassword");
			env.put(Context.PROVIDER_URL, "ldaps://ldapserver.company.com:636"); //port 389/636. 3268/2001 ?
			
			ctx = new InitialLdapContext(env, null);
			System.out.println("Connection Successful.");
		}catch(NamingException nex){
			System.out.println("LDAP Connection: FAILED");
			nex.printStackTrace();
		}
		return ctx;
	}

	private void getUserBasicAttributes(String username, LdapContext ctx) {
		
		try {

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "distinguishedName", "id", "name",
					"sn",
					"givenname",
					"mail",
					"telephonenumber"};
			constraints.setReturningAttributes(attrIDs);
			//First input parameter is search bas, it can be "CN=Users,DC=YourDomain,DC=com"
			//Second Attribute can be uid=username
			//NamingEnumeration answer = ctx.search("CN=Users,DC=yourDomain,DC=com", "sAMAccountName="
			NamingEnumeration answer = ctx.search("OU=People,DC=shops,DC=company,DC=com", "sAMAccountName="
					+ username, constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				System.out.println("distinguishedName "+ attrs.get("distinguishedName"));
				System.out.println("id "+ attrs.get("id"));
				System.out.println("name "+ attrs.get("name"));
				System.out.println("givenname "+ attrs.get("givenname"));
				System.out.println("sn "+ attrs.get("sn"));
				System.out.println("mail "+ attrs.get("mail"));
				System.out.println("telephonenumber "+ attrs.get("telephonenumber"));
			}else{
				throw new Exception("Invalid User");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
