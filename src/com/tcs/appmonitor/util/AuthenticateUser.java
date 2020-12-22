package com.tcs.appmonitor.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

public class AuthenticateUser {

	static final Logger LOGGER = Logger.getLogger(AuthenticateUser.class);

	public static Hashtable authenticateUser(String domain, String username, String password, String ldapUrl) {
		Hashtable htx = null;
		try {	  
			
			  htx = getAnyUserAttr(username, TYPE_USERNAME, ldapUrl); String htxdomain =
			  (String)htx.get("DOMAIN"); LOGGER.info("Found user " + username + " in  " +
			  htxdomain); if(htx != null && htxdomain.equalsIgnoreCase(domain)) { Hashtable
			  ht = new Hashtable(5, 0.75F); ht.put("java.naming.factory.initial",
			  "com.sun.jndi.ldap.LdapCtxFactory"); ht.put("java.naming.provider.url",
			  ldapUrl); ht.put(Context.SECURITY_AUTHENTICATION, "Simple");
			  ht.put(Context.REFERRAL, "follow"); ht.put(Context.SECURITY_PRINCIPAL,
			  domain+"\\"+username); ht.put(Context.SECURITY_CREDENTIALS, password);
			  
			  InitialDirContext localInitialDirContext = new InitialDirContext(ht);
			  if(localInitialDirContext != null) return htx;
			  
			  } return null;
			
			/*
			 * htx = new Hashtable<>(); htx.put("sAMAccountName", "mjnb9x");
			 * htx.put("displayName", "Mufaddal Kagzi"); htx.put("mail",
			 * "mufaddal.kagzi@delphi.com"); return htx;
			 */
			 

		} catch (Exception ex) {
			System.err.println("Exception: " + ex.getMessage());
			LOGGER.error("Exception: " + ex.getMessage());
			return null;
		}
	}

	/*
	 * Lookup any user from Delphi Active Directory (AD) using LDAP This uses an
	 * application ID (CN=EIT Applications) to perform a general lookup. It only
	 * validates that the user exists and gets their attributes. The user's
	 * credentials are not validated.
	 */

	public static final int TYPE_USERNAME = 0;
	public static final int TYPE_EMAIL = 1;
	public static final int TYPE_NAME = 2;

	public static Hashtable getAnyUserAttr(String searchCriteria, int searchType, String ldapUrl) throws Exception {
		Hashtable ht = null;
		LdapContext ctx = null;
		try {
			ctx = getLdapContext(ldapUrl);

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "sAMAccountName", "uid", "distinguishedName", "sn", "cn", "givenname", "displayName",
					"mail", "telephonenumber", "streetAddress", "co", "l", "st", "extensionAttribute4" };
			constraints.setReturningAttributes(attrIDs);

			NamingEnumeration answer = null;
			switch (searchType) {
			case AuthenticateUser.TYPE_USERNAME:
			default:
				answer = ctx.search("OU=Sites,OU=DELPHIDRIVE,DC=delphidrive,DC=com", "sAMAccountName=" + searchCriteria,
						constraints);
				break;

			case AuthenticateUser.TYPE_EMAIL:
				answer = ctx.search("OU=Sites,OU=DELPHIDRIVE,DC=delphidrive,DC=com", "mail=" + searchCriteria,
						constraints);
				break;

			case AuthenticateUser.TYPE_NAME:
				answer = ctx.search("OU=Sites,OU=DELPHIDRIVE,DC=delphidrive,DC=com",
						"displayName=" + searchCriteria + "*", constraints);
				break;
			}

			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				ht = new Hashtable();
				ht.put("sAMAccountName", getAttributeValue("sAMAccountName", attrs));
				ht.put("cn", getAttributeValue("cn", attrs));
				ht.put("distinguishedName", getAttributeValue("distinguishedName", attrs));
				ht.put("givenname", getAttributeValue("givenname", attrs));
				ht.put("displayName", getAttributeValue("displayName", attrs));
				ht.put("sn", getAttributeValue("sn", attrs));
				ht.put("mail", getAttributeValue("mail", attrs) != null ? getAttributeValue("mail", attrs) : "");
				ht.put("telephonenumber",
						getAttributeValue("telephonenumber", attrs) != null
								? getAttributeValue("telephonenumber", attrs)
								: "");
				ht.put("DOMAIN", "DELPHIDRIVE");
				ht.put("streetAddress",
						getAttributeValue("streetAddress", attrs) != null ? getAttributeValue("streetAddress", attrs)
								: "");
				ht.put("co", getAttributeValue("co", attrs) != null ? getAttributeValue("co", attrs) : "");
				ht.put("l", getAttributeValue("l", attrs) != null ? getAttributeValue("l", attrs) : "");
				ht.put("st", getAttributeValue("st", attrs) != null ? getAttributeValue("st", attrs) : "");
				ht.put("extensionAttribute4",
						getAttributeValue("extensionAttribute4", attrs) != null
								? getAttributeValue("extensionAttribute4", attrs)
								: "");

			}

		} catch (Exception ex) {
			throw ex;
		}

		return ht;
	}

	private static String getAttributeValue(String attributeName, Attributes attrs) {
		if (attributeName == null || attrs == null) {
			return null;
		}
		Attribute a = attrs.get(attributeName);
		if (a != null) {
			return a.toString().substring(attributeName.length() + 2);
		}
		return null;
	}

	private static LdapContext getLdapContext(String ldapServer) throws Exception {
		LdapContext ctx = null;

		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL,
					"CN=SID_TCUIDR,OU=SID ServiceID,OU=Service Accounts,OU=Administration,OU=DELPHIDRIVE,DC=DelphiDrive,DC=com");
			env.put(Context.SECURITY_CREDENTIALS, "Jhg3ZwvK");
			env.put(Context.PROVIDER_URL, ldapServer);
			ctx = new InitialLdapContext(env, null);
		} catch (NamingException nex) {
			throw new Exception(nex.getMessage());
		}

		return ctx;
	}

}
