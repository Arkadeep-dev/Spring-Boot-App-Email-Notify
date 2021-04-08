package com.employee.api.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import com.employee.api.entity.Employee;

public interface EmployeeService {
	
	public List <Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeById(String id);
	
	public void addEmployee(Employee employee);

	public void deleteEmployeeById(String id);

	public void resignEmployee(String id);

}
