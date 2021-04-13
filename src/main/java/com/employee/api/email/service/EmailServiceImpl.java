package com.employee.api.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.employee.api.decrypt.DecryptPassword;
import com.employee.api.email.entity.Email;
import com.employee.api.entity.Employee;
import com.employee.api.exceptions.ProjectException;

@Service
public class EmailServiceImpl implements EmailServiceable {

	// getting group email address from properties file
	@Value("${email.group}")
	private String groupEmailAddress;

	// getting employee email address from properties file
	@Value("${email.from.address}")
	private String fromEmailAddress;

	// getting employee password from properties file
	@Value("${email.from.password}")
	private String fromEmailPassword;

	// getting mail host from properties file
	@Value("${email.host}")
	private String host;

	// getting mail port from properties file
	@Value("${email.port}")
	private Integer port;

	// getting mail ssl.enable value from properties file
	@Value("${email.smtp.auth}")
	private boolean sslEnable;

	// getting mail ssl.auth value from properties file
	@Value("${email.smtp.ssl.enable}")
	private boolean sslAuth;

	@Override
	public void resignEmployee(Employee employee) {
		
		Email email = new Email();
		email.setTo(groupEmailAddress);
		email.setFrom(fromEmailAddress);
		email.setFromPassword(fromEmailPassword);
		email.setSubject("Resignation letter");
		email.setMessage(
				"Employee is resigning with eID \t=" + employee.getEmpId() + " \t eName \t" + employee.getEmpName()
						+ "\t eProject \t" + employee.getEmpProject() + "\t on \t" + employee.getEmpDOR());
		
		// get system properties
		Properties properties = System.getProperties();
		// setting info in properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.ssl.enable", sslEnable);
		properties.put("mail.smtp.auth", sslAuth);
		
		new EmailServiceImpl().sendEmail(email, properties);
	}

	public void sendEmail(Email email, Properties properties) {

		// getting the session object and setting the properties and authenticating
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFrom(), DecryptPassword.decrypt(email.getFromPassword()));
			}

		});

		session.setDebug(true);
		// creating the mail
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(email.getFrom());
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
			mimeMessage.setSubject(email.getSubject());
			mimeMessage.setText(email.getMessage());
			Transport.send(mimeMessage);
		} catch (Exception ex) {
			throw new ProjectException(ex.getMessage());
		}

	}
}
