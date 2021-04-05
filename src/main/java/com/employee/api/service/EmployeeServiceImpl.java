package com.employee.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.api.dao.EmployeeDao;
import com.employee.api.email.service.EmailService;
import com.employee.api.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmailService emailService;
	@Autowired
	EmployeeDao dao;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return dao.getEmployeeById(id);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		employee.setEmpDOJ(Timestamp.valueOf(date));
		employee.setEmpDOR(null);
		dao.save(employee);
	}

	@Override
	public void deleteEmployeeById(String id) {
		// TODO Auto-generated method stub
		dao.deleteEmployeeById(id);
	}

	@Override
	public boolean resignEmployee(String id) {
		// TODO Auto-generated method stub

		Optional<Employee> e = dao.getEmployeeById(id);
		Employee employee;
		if (!e.isEmpty())
			employee = e.get();
		else
			throw new NullPointerException();
		LocalDateTime lt = LocalDateTime.now().plusMonths(2);
		Timestamp t= Timestamp.valueOf(lt);
		employee.setEmpDOR(t);
		dao.resignEmployee(id, t);
		boolean email= emailService.resignEmployee(employee);

		if(email)
			return true;
		else
			return false;
		
	}

}
