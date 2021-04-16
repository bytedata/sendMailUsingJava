package com.sk;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static void mailAuthenticator(String recipient) throws MessagingException {
		System.out.println("Preapering to send email...");
		System.out.println("...");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myEmail = "youremail@gmail.com";
		String myPass = "yourpassword";

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPass);

			}
		});
		Message message = createMessage(session, myEmail, myEmail);
		Transport.send(message);
		System.out.println("...");
		System.out.println("Messsage send successfully !!!");
	}

	private static Message createMessage(Session session, String myEmail, String recipient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Confidential mail, do not reply this mail.");
			message.setText("Hope you are doing good..., \n\n do your home work.");
			return message;
		} catch (Exception e) {
			Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;

	}
}
