package br.unitins.sac.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dswebunitins@gmail.com", "123dsweb");
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("dswebunitins@gmail.com"));
			Address[] toUser = InternetAddress.parse("janiojunior@gmail.com");
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("teste");
			message.setText("Texto", "utf-8", "html");

			Transport.send(message);
			System.out.println("Email enviado!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		

	}

}
