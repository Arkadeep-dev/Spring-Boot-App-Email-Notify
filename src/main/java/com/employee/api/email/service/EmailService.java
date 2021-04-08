package com.employee.api.email.service;

import javax.mail.MessagingException;

import com.employee.api.entity.Employee;

public interface EmailService {
		
	public boolean resignEmployee(Employee employee);

}
