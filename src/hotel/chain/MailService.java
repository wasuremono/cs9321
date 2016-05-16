package hotel.chain;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	void sendMail(String recipientEmail, String subject, String messageBody){
	final String from = "cs9321ass2hotel@gmail.com";
	final String password = "randompassword";
	String smtp = "smtp.gmail.com";
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
	Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			  });
	System.out.println("Preparing Email");
	try {
	      Message msg = new MimeMessage(session);
	      msg.setFrom(new InternetAddress(from));
	      msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("wasuremono.s@gmail.com"));
	      msg.setSubject(subject);
	      msg.setContent(messageBody, "text/html");
	      Transport.send(msg);
	      System.out.println("Email Sent");
	    } catch (AddressException e) {
	    	System.out.println("Address");
	    } catch (MessagingException e) {
	    	System.out.println(e);
	    }
	  }
}
