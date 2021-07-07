package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Employee;
import service.AchievementService;
import service.EducationService;
import service.ProjectService;
import service.SkillService;

public class EmployeeDao implements EmployeeDaoInterface{

	private static EmployeeDao instance;
	private List<Employee> employees;
	private static int available_id;
	
	private EmployeeDao()
	{
		this.employees = new ArrayList<Employee>(Arrays.asList(
				new Employee(1,"Omar Faruque","Abir","01759389123","github.com/ofabir9","linkedin.com/in/ofabir9","omarfaruqueabir@gmail.com","13/2 Paikpara D-type Quarter, Mirpur-1, Dhaka-1216",new EducationService().getEducations(),new SkillService().getSkills(),new ProjectService().getProjects(),new AchievementService().getAchievements()),
				new Employee(2,"Afikur Rahman","Khan","01759389124","github.com/afikur","linkedin.com/in/afikur","afikur@gmail.com","23/7 Taltola, Mirpur-2, Dhaka-1216",new EducationService().getEducations(),new SkillService().getSkills(),new ProjectService().getProjects(),new AchievementService().getAchievements()),
				new Employee(3,"Sazzad","Hossain","01759389125","github.com/szdhossain","linkedin.com/in/szdhossain","szdhossain@gmail.com","56/5 Rupnagar, Mirpur-6, Dhaka-1216",new EducationService().getEducations(),new SkillService().getSkills(),new ProjectService().getProjects(),new AchievementService().getAchievements())					
				));
		available_id = this.employees.size()+1;
	}
	
	public static EmployeeDao getEmployeeDao()
	{
		if(instance==null)
        {
            synchronized (EmployeeDao.class)
            {
                if(instance==null)
                {
                    instance=new EmployeeDao();
                }
            }
        }
        return instance;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return this.employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		for(Employee employee : employees)
		{
			if(employee.getId() == id)
			{
				return employee;
			}
		}
		return null;
	}

	@Override
	public void insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.employees.add(employee);
		EmployeeDao.available_id++;
		
	}

	@Override
	public int getAvailableId() {
		// TODO Auto-generated method stub
		return EmployeeDao.available_id;
	}
	
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employees.remove(employee);
	}

	@Override
	public void updateEmployee(Employee oldEmployee, Employee newEmployee) {
		// TODO Auto-generated method stub
		System.out.println("Updating");
		deleteEmployeeById(oldEmployee.getId());
		insertEmployee(newEmployee);
	}

	@Override
	public void deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		for(Employee employee: employees){
			if(employee.getId() == id)
			{
				employees.remove(employee);
				break;
			}
		}
	}

	

	

}
