import java.util.*;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;

public class EmailReader2 {

    public static void main(String[] args) {
    
	Properties properties = new Properties();
	properties.put("mail.transport.protocol", "smtp");
	properties.put("mail.smtp.host", "mailserver.company.com");
	properties.put("mail.smtp.port", "2525");
	properties.put("mail.smtp.auth", "true");

	final String username = "username";
	final String password = "password";
	Authenticator authenticator = new Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(username, password);
	    }
	};

	Transport transport = null;

	try {
	    Session session = Session.getDefaultInstance(properties, authenticator);
	    //MimeMessage mimeMessage = createMimeMessage(session, mimeMessageData);
	    //MimeMessage mimeMessage = createMimeMessage(session);
	    //mimeMessage = new MimeMessage(session);
	    Message mimeMessage = new MimeMessage(session);
	    transport = session.getTransport();
	    transport.connect(username, password);
	    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	} finally {
	    if (transport != null) try { transport.close(); } catch (MessagingException logOrIgnore) {}
	}
	
    }
}
