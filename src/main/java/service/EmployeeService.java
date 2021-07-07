package service;
import java.util.List;

import DAO.EmployeeDao;
import DAO.EmployeeDaoInterface;
import model.Employee;

public class EmployeeService {
	
	private static EmployeeService instance;
	EmployeeDaoInterface employeeDao;
	
	private EmployeeService(EmployeeDaoInterface employeeDao)
	{
		this.employeeDao = employeeDao;
	}
	
	public List<Employee> getAllEmployees()
	{
		return employeeDao.getAllEmployees();
	}
	
	public Employee getEmployeeById(int id)
	{
		return employeeDao.getEmployeeById(id);
		
	}
	public void insertEmployee(Employee employee)
	{
		this.employeeDao.insertEmployee(employee);
	}
	public static EmployeeService getEmployeeService()
	{
		if(instance==null)
        {
            synchronized (EmployeeService.class)
            {
                if(instance==null)
                {
                    instance=new EmployeeService(EmployeeDao.getEmployeeDao());
                }
            }
        }
        return instance;
	}
	public int getAvailableId()
	{
		return this.employeeDao.getAvailableId();
	}
	public void deleteEmployee(Employee employee)
	{
		employeeDao.deleteEmployee(employee);
	}
	public void updateEmployee(Employee oldEmployee,Employee newEmployee)
	{
		employeeDao.updateEmployee(oldEmployee,newEmployee);
	}
	
}
