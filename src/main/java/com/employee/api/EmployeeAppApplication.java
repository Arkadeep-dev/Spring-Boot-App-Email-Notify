package com.employee.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.api.dao.EmployeeDaoable;

@SpringBootApplication
public class EmployeeAppApplication implements CommandLineRunner {

	@Autowired
	private EmployeeDaoable dao;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dao.createTable();
	}

}
