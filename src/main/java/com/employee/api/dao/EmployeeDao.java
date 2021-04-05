package com.employee.api.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.employee.api.entity.Employee;

public interface EmployeeDao {
	
	public void  createTable();

	public List <Employee> getAll();
	
	public Optional<Employee> getEmployeeById(String id);
	
	public void save(Employee employee);

	public void deleteEmployeeById(String id);

	public void resignEmployee(String id, Timestamp t);

}
