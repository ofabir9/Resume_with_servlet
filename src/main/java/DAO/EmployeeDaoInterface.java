package DAO;

import java.util.List;

import model.Employee;

public interface EmployeeDaoInterface {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public void insertEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public void deleteEmployeeById(int id);
	public void updateEmployee(Employee oldEmployee,Employee newEmployee);
	public int getAvailableId();
}
