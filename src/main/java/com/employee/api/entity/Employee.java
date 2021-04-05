package com.employee.api.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	private String empId;
	private String empName;
	private double empSalary;
	private String empProject;
	private Timestamp empDOJ;
	private Timestamp empDOR;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empId, String empName, double empSalary, String empProject, Timestamp empDOJ,
			Timestamp empDOR) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.empProject = empProject;
		this.empDOJ = empDOJ;
		this.empDOR = empDOR;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public String getEmpProject() {
		return empProject;
	}

	public void setEmpProject(String empProject) {
		this.empProject = empProject;
	}

	public Timestamp getEmpDOJ() {
		return empDOJ;
	}

	public void setEmpDOJ(Timestamp empDOJ) {
		this.empDOJ = empDOJ;
	}

	public Timestamp getEmpDOR() {
		return empDOR;
	}

	public void setEmpDOR(Timestamp empDOR) {
		this.empDOR = empDOR;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", empProject="
				+ empProject + ", empDOJ=" + empDOJ + ", empDOR=" + empDOR + "]";
	}

	
}
	
	

	