package com.employee.api.email.entity;

import org.springframework.stereotype.Component;

@Component
public class Email {

	private String to;
	private String from;
	private String fromPassword;
	private String subject;
	private String message;

	public Email() {
	}

	public Email(String to, String from, String fromPassword, String subject, String message) {
		super();
		this.to = to;
		this.from = from;
		this.fromPassword = fromPassword;
		this.subject = subject;
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Email [to=" + to + ", from=" + from + ", fromPassword=" + fromPassword + ", subject=" + subject
				+ ", message=" + message + "]";
	}

}
