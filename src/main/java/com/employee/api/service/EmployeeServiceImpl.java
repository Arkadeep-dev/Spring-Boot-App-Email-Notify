package com.employee.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.api.dao.EmployeeDaoable;
import com.employee.api.email.service.EmailServiceable;
import com.employee.api.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeServiceable {

	@Autowired
	EmailServiceable emailService;
	@Autowired
	EmployeeDaoable dao;

	// get all the employees
	@Override
	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}

	// get one employee by id
	@Override
	public Optional<Employee> getEmployeeById(String id) {
		return dao.getEmployeeById(id);
	}

	// add one employee
	@Override
	public void addOneEmployee(Employee employee) {
		LocalDateTime dateTime = LocalDateTime.now();
		// setting employee date of joining
		employee.setEmpDOJ(Timestamp.valueOf(dateTime));
		// setting employee date of resignation as null
		employee.setEmpDOR(null);

		dao.saveOneEmployee(employee);
	}

	// delete a employee by id
	@Override
	public void deleteEmployeeById(String id) {
		dao.deleteEmployeeById(id);
	}

	// resign a employee by id
	@Override
	public void resignEmployeeById(String id) {
		// getting the employee by the id
		Optional<Employee> employee = dao.getEmployeeById(id);
		// storing the time 2 months after the current date
		LocalDateTime dateTimeAfterTwoMonths = LocalDateTime.now().plusMonths(2);
		Timestamp timeStamp = Timestamp.valueOf(dateTimeAfterTwoMonths);
		// updating the employee resignation date in the db
		dao.resignEmployee(id, timeStamp);
		// sending an email to the group email of the employee
		if (employee.isPresent()) {
			employee.get().setEmpDOR(timeStamp);
			emailService.resignEmployee(employee.get());
		}
					
	}

}
