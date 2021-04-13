package com.employee.api.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.api.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDaoable {

	public static final String CREATE_TABLE_EMPLOYEE = "create table if not exists employee  (eId varchar(5) primary key not null ,eName varchar(10) ,eSalary decimal (8,2),eProject varchar(10),eDOJ TIMESTAMP,eDOR TIMESTAMP);";         
	public static final String INSERT_EMPLOYEE_QUERY = "Insert into employee values(?,?,?,?,?,?)";
	public static final String GET_ALL_EMPLOYEE_QUERY = "select * from employee";
	public static final String GET_EMPLOYEE_BY_ID_QUERY = "select * from employee where eID= ?";
	public static final String DELETE_EMPLOYEE_BY_ID_QUERY = "delete from employee where eID=?";
	public static final String UPDATE_EMPLOYEE_BY_ID_QUERY = "update employee set eDOR=? where eID=?";

	@Autowired
	private JdbcTemplate jt;

	org.springframework.jdbc.core.RowMapper<Employee> rowMapper = (rs, rowNum) -> {
		return new Employee(rs.getString("eId"), rs.getString("eName"), rs.getDouble("eSalary"),
				rs.getString("eProject"), rs.getTimestamp("eDOJ"), rs.getTimestamp("eDOR"));
	};
	
	//create the desired table if not present
	public void createTable() {
		jt.update(CREATE_TABLE_EMPLOYEE);
	}

	//get all employees
	@Override
	public List<Employee> getAllEmployees() {
		return jt.query(GET_ALL_EMPLOYEE_QUERY, rowMapper);
	}

	//get one employee by id
	@Override
	public Optional<Employee> getEmployeeById(String id) {
		Employee employee = jt.queryForObject(GET_EMPLOYEE_BY_ID_QUERY, rowMapper,id);
		return Optional.ofNullable(employee);
	}

	//add one employee
	@Override
	public void saveOneEmployee(Employee employee) {
		jt.update(INSERT_EMPLOYEE_QUERY, employee.getEmpId(), employee.getEmpName(), employee.getEmpSalary(), employee.getEmpProject(),
				employee.getEmpDOJ(), employee.getEmpDOR());
	}

	//delete one employee by id
	@Override
	public void deleteEmployeeById(String id) {
		jt.update(DELETE_EMPLOYEE_BY_ID_QUERY, id);
	}

	//updating the resignation date of the employee by id
	@Override
	public void resignEmployee(String id, Timestamp timestamp) {
		jt.update(UPDATE_EMPLOYEE_BY_ID_QUERY, timestamp, id);

	}

}
