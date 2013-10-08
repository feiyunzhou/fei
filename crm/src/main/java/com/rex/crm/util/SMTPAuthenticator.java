package com.rex.crm.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator{
	 String username = ""; 
	  
	 String password = ""; 
	 public SMTPAuthenticator() { 
		 super(); 
	 } 
    public SMTPAuthenticator(String user,String pwd){ 
	    super(); 
	    username = user; 
	    password = pwd; 
    } 
    public PasswordAuthentication getPasswordAuthentication(){ 
	    return new PasswordAuthentication(username,password); 
    } 
}
