import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailReader3 {

  public static void main(String[] args) throws Exception {

    Properties props = new Properties();
    //props.put("mail.host", "mail.cloud9.net");
    props.put("mail.host", "www.gmail.com");

    Session mailConnection = Session.getInstance(props, null);
    Message msg = new MimeMessage(mailConnection);

    Address a = new InternetAddress("a@a.com", "A a");
    Address b = new InternetAddress("fake@java2s.com");

    msg.setContent("Mail contect", "text/plain");
    msg.setFrom(a);
    msg.setRecipient(Message.RecipientType.TO, b);
    msg.setSubject("subject");

    Transport.send(msg);
  }
}
