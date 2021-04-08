package com.employee.api.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.entity.Employee;
import com.employee.api.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get all the employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		if (list.size() <= 0) {
			throw new NullPointerException();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	// get a employee
	@GetMapping("/employee/{id}")
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable String id) {
		Optional<Employee> emp = employeeService.getEmployeeById(id);
		if (emp == null) {
			throw new NullPointerException();
		}
		return ResponseEntity.of(Optional.of(emp));
	}

	// add employee
	@PostMapping("/employee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);

	}

	// delete employee
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// resign employee
	@PostMapping("/employee/resign/{id}")
	public ResponseEntity<?> resignEmployee(@PathVariable String id) {

		employeeService.resignEmployee(id);
		return ResponseEntity.ok("Done");

	}
}
