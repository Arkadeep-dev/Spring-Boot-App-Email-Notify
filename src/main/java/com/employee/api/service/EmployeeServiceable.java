package com.employee.api.service;

import java.util.List;
import java.util.Optional;

import com.employee.api.entity.Employee;

public interface EmployeeServiceable {
	
	// get all the employees
	public List <Employee> getAllEmployees();
	
	//get one employee by id
	public Optional<Employee> getEmployeeById(String id);
	
	//add one employee 
	public void addOneEmployee(Employee employee);

	//delete a employee by id
	public void deleteEmployeeById(String id);

	//resign a employee by id
	public void resignEmployeeById(String id);

}
