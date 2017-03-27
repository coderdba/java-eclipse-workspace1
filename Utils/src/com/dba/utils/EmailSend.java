package com.dba.utils;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class EmailSend  
{  
 public static void main(String [] args){  
      String to = "myemail@yahoo.com";
      String from = "myemail@laptop.com"; // a phony/realfrom address
      String host = "mailhost.target.com";
 
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.setProperty("mail.smtp.host", host);  
      
  	  //properties.setProperty("mail.smtp.auth", "true");
      //properties.setProperty("mail.smtp.starttls.enable", "true"); //for tls
      properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //for ssl
      
      
      Session session = Session.getDefaultInstance(properties);  
  
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("Ping");  
         message.setText("Hello, this is example of sending email  ");  

         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
}  
