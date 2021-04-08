package com.employee.api.email.service;

import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.employee.api.decrypt.DecryptPassword;
import com.employee.api.email.entity.Email;
import com.employee.api.entity.Employee;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${email.groupAddress}")
	private String groupEmailAddress;

	@Value("${email.from.address}")
	private String fromEmailAddress;

	@Value("${email.from.password}")
	private String fromEmailPassword;

	@Override
	public boolean resignEmployee(Employee e) {
		// TODO Auto-generated method stub
		System.out.println();
		Email email = new Email();
		email.setTo(groupEmailAddress);
		email.setFrom(fromEmailAddress);
		email.setFromPassword(fromEmailPassword);
		email.setSubject("Resignation letter");
		email.setMessage("Employee is resigning with eID \t=" + e.getEmpId() + " \t eName \t" + e.getEmpName()
				+ "\t eProject \t" + e.getEmpProject() + "\t on \t" + e.getEmpDOR());

		return EmailServiceImpl.sendEmail(email);
	}

	private static boolean sendEmail(Email e) {

		boolean flag = false;

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(e.getFrom(), DecryptPassword.decrypt(e.getFromPassword()));
			}

		});

		session.setDebug(true);
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(e.getFrom());
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(e.getTo()));
			mimeMessage.setSubject(e.getSubject());
			mimeMessage.setText(e.getMessage());
			Transport.send(mimeMessage);

			flag = true;
		} catch (Exception ex) {
			// TODO: handle exception
			throw new RuntimeException(ex);
		}
		System.out.println(flag);
		return flag;

	}
}
