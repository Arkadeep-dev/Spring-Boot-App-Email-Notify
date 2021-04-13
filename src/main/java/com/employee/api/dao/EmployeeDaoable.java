package com.employee.api.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.employee.api.entity.Employee;

public interface EmployeeDaoable {

	//create the desired table if not present
	public void createTable();
	
	//get all employees
	public List <Employee> getAllEmployees();
	
	//get one employee by id
	public Optional<Employee> getEmployeeById(String id);
	
	//add one employee
	public void saveOneEmployee(Employee employee);

	//delete one employee by id
	public void deleteEmployeeById(String id);

	//updating the resignation date of the employee by id
	public void resignEmployee(String id, Timestamp t);

	

}
