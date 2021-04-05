package com.employee.api.service;

import java.util.List;
import java.util.Optional;

import com.employee.api.entity.Employee;

public interface EmployeeService {
	
	public List <Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeById(String id);
	
	public void addEmployee(Employee employee);

	public void deleteEmployeeById(String id);

	public boolean resignEmployee(String id);

}
