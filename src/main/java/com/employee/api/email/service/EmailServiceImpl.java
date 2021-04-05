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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.employee.api.email.entity.Email;
import com.employee.api.entity.Employee;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${email.groupAddress}")
	private String groupEmailAddress;
	
	@Value("${email.from.address}")
	private  String fromEmailAddress;
	
	@Value("${email.from.password}")
	private  String fromEmailPassword;
	
	
	@Override
	public boolean resignEmployee(Employee e) {
		// TODO Auto-generated method stub
			System.out.println();
		Email email = new Email();
		email.setTo(groupEmailAddress);
		email.setFrom(fromEmailAddress);
		email.setFromPassword(fromEmailPassword);
		email.setSubject("Resignation letter");
		email.setMessage("Employee is resigning with eID \t="+
						e.getEmpId()+" \t eName \t"+e.getEmpName()+"\t eProject \t"+
						e.getEmpProject()+"\t on \t"+e.getEmpDOR());
		
		return EmailServiceImpl.sendEmail(email);
	}

	private static boolean sendEmail(Email e) {
		
		boolean flag = false;
		
		// variable for gmail host
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		// setting important info to properties object
		// set host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(e.getFrom(),e.getFromPassword());
			}

		});
		
		session.setDebug(true);
		
		//step 2: compose the message
		MimeMessage mimeMessage = new MimeMessage(session);
		try
		{
		//from email
		mimeMessage.setFrom(e.getFrom());
		
		//adding recipient to message
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(e.getTo()));
		
		//adding subject to message
		mimeMessage.setSubject(e.getSubject());
		
		//adding text to message
		mimeMessage.setText(e.getMessage());
		
		//Step 3: send the message using transport class
		Transport.send(mimeMessage);
		
		flag=true;
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		System.out.println(flag);
		return flag;
		
	}

	
}
