package com.employee.api.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.api.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	public static final String CREATE_TABLE_EMPLOYEE = "create table if not exists employee  (\r\n"
			+ "	eId varchar(5) primary key not null ,\r\n"
			+ "	eName varchar(10) ,\r\n"
			+ "	eSalary decimal (8,2),\r\n"
			+ "	eProject varchar(10),\r\n"
			+ "	eDOJ TIMESTAMP,\r\n"
			+ "	eDOR TIMESTAMP\r\n"
			+ ");";
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
	
	public void createTable() {
		jt.update(CREATE_TABLE_EMPLOYEE);
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub

		return jt.query(GET_ALL_EMPLOYEE_QUERY, rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<Employee> getEmployeeById(String id) {
		// TODO Auto-generated method stub
		Employee e = jt.queryForObject(GET_EMPLOYEE_BY_ID_QUERY, new Object[] { id }, rowMapper);
		return Optional.ofNullable(e);
	}

	@Override
	public void save(Employee e) {
		// TODO Auto-generated method stub
		jt.update(INSERT_EMPLOYEE_QUERY, e.getEmpId(), e.getEmpName(), e.getEmpSalary(), e.getEmpProject(),
				e.getEmpDOJ(), e.getEmpDOR());
	}

	@Override
	public void deleteEmployeeById(String id) {
		// TODO Auto-generated method stub
		jt.update(DELETE_EMPLOYEE_BY_ID_QUERY, id);
	}

	@Override
	public void resignEmployee(String id, Timestamp t) {
		// TODO Auto-generated method stub
		jt.update(UPDATE_EMPLOYEE_BY_ID_QUERY, t, id);

	}

}
