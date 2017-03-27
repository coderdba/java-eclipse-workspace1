
	import java.util.*;
	import javax.mail.*;
	
	public class EmailReader {
	    public static void main(String[] args) {
	        Properties props = new Properties();
	        props.setProperty("mail.store.protocol", "imaps");
	        try {
	            Session session = Session.getInstance(props, null);
	            Store store = session.getStore();
	            //store.connect("imap.gmail.com", "yourEmailId@gmail.com", "password");
	            //store.connect("https://mailserver.company.com/owa", "firstname.lastname@company.com", "password");
	            store.connect("mailserver.company.com", "firstname.lastname@company.com", "password");
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);
	            Message msg = inbox.getMessage(inbox.getMessageCount());
	            Address[] in = msg.getFrom();
	            for (Address address : in) {
	                System.out.println("FROM:" + address.toString());
	            }
	            Multipart mp = (Multipart) msg.getContent();
	            BodyPart bp = mp.getBodyPart(0);
	            System.out.println("SENT DATE:" + msg.getSentDate());
	            System.out.println("SUBJECT:" + msg.getSubject());
	            System.out.println("CONTENT:" + bp.getContent());
	        } catch (Exception mex) {
	            mex.printStackTrace();
	        }
	    }
	}
	
