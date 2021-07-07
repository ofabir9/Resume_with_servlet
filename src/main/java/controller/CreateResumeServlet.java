package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeDao;
import model.Employee;
import model.Education;
import model.Skill;
import model.Project;
import model.Achievement;

import service.EmployeeService;
import validator.EmployeeValidator;

import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.net.URL;
/**
 * Servlet implementation class CreateResumeServlet
 */
@WebServlet("/CreateResumeServlet")
public class CreateResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		processGetRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(request.getParameter("fname")+" "+request.getParameter("lname"));
		processPostRequest(request, response);
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeServlet");
		dispatcher.forward(request, response);
		*/
	}
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/createResume.jsp");
		dispatcher.forward(request, response);
	}
	public void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
//		PrintWriter out = response.getWriter();
//        response.setContentType("text/plain");
// 
//        Enumeration<String> parameterNames = request.getParameterNames();
// 
//        while (parameterNames.hasMoreElements()) {
// 
//            String paramName = parameterNames.nextElement();
//            out.write(paramName);
//            out.write("\n");
// 
//            String[] paramValues = request.getParameterValues(paramName);
//            for (int i = 0; i < paramValues.length; i++) {
//                String paramValue = paramValues[i];
//                out.write("\t" + paramValue);
//                out.write("\n");
//            }
// 
//        }   
//        out.write("available Id "+id);
        
		
        Employee employee = new Employee();
        
        employee.setId(EmployeeService.getEmployeeService().getAvailableId());
        employee.setFirstName(request.getParameter("fname"));
        employee.setLastName(request.getParameter("lname"));
        employee.setMobile(request.getParameter("mobile"));
        employee.setGithub(request.getParameter("github"));
        employee.setLinkedin(request.getParameter("linkedin"));
        employee.setGmail(request.getParameter("gmail"));
        employee.setAddress(request.getParameter("address"));
        
        
        
//        out.write("Employee name "+employee.getFullName());
        
        
        List<Education> educations = new ArrayList<Education>();
        List<Skill> skills = new ArrayList<Skill>();
        List<Project> projects = new ArrayList<Project>();
        List<Achievement> achievements = new ArrayList<Achievement>();
        
        
        String[] educationCourses = request.getParameterValues("educationCourse[]");
        String[] educationSubjects = request.getParameterValues("educationSubject[]");
        String[] educationInstitutions = request.getParameterValues("educationInstitution[]");
        String[] educationPassingYears = request.getParameterValues("educationPassingYear[]");
        String[] educationAchievedGrades = request.getParameterValues("educationAchievedGrade[]");
        String[] educationTotalGrades = request.getParameterValues("myeducationTotalGrade[]");
        
        String[] skillTypes = request.getParameterValues("skillType[]");
        String[] skillNamess = request.getParameterValues("skillNames[]");
        
        String[] projectNames = request.getParameterValues("projectName[]");
        String[] projectDescriptions = request.getParameterValues("projectDescription[]");
        String[] projectLanguagess = request.getParameterValues("projectLanguages[]");
        String[] projectToolss = request.getParameterValues("projectTools[]");
        
        String[] achievementNames = request.getParameterValues("achievementName[]");
        String[] achievementDescriptions = request.getParameterValues("achievementDescription[]");
        
        
        if(educationCourses!=null)
        for (int i = 0; i < educationCourses.length; i++) {
//        	out.write("\nDebug\n "+educationCourses[i]+"\n");
            educations.add(new Education(educationCourses[i],educationSubjects[i],educationInstitutions[i],educationPassingYears[i],Float.parseFloat(educationAchievedGrades[i]),Float.parseFloat(educationTotalGrades[i])));
        }
        employee.setEducations(educations);
        
//        out.write("Employee name "+employee.getFullName());  
        if(skillTypes!=null)
        for (int i = 0; i < skillTypes.length; i++) {
        	List<String> skillNames = Arrays.asList();
        	String[] values = skillNamess[i].split(",");
        	skillNames = new ArrayList<String>(Arrays.asList(values));
        	skills.add(new Skill(skillTypes[i],skillNames));
        }
        employee.setSkills(skills);

//        out.write("\nDebug ProjectNames\n "+projectNames+"\n");
        if(projectNames!=null)
        for (int i = 0; i < projectNames.length; i++) {  
        	
        	List<String> projectLanguages = Arrays.asList();
        	String[] values = projectLanguagess[i].split(",");
        	projectLanguages = new ArrayList<String>(Arrays.asList(values));
//        	out.write("\nDebug project\n "+projectLanguages+"\n");
        	
        	List<String> projectTools = Arrays.asList();
        	values = projectToolss[i].split(",");
        	projectTools = new ArrayList<String>(Arrays.asList(values));
//        	out.write("\nDebug project\n "+projectTools+"\n");
        	
        	projects.add(new Project(projectNames[i],projectDescriptions[i],projectLanguages,projectTools));
        }
        employee.setProjects(projects);
          
        if(achievementNames!=null)
        for(int i = 0; i < achievementNames.length ; i++ )
        {
//        	out.write("\nDebug project\n "+achievementNames[i]+"\n");
        	achievements.add(new Achievement(achievementNames[i],achievementDescriptions[i]));
        }
        employee.setAchievements(achievements);
        
        
        
        List<String>errors = EmployeeValidator.getErrors(employee);
        
        if(errors.isEmpty())
        {

        	EmployeeService.getEmployeeService().insertEmployee(employee);
            response.sendRedirect("EmployeeServlet");
	    }
        else
        {
	         request.setAttribute("errors", errors);
	         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/createResume.jsp");
	         dispatcher.forward(request, response);
	    }
        
        
//        out.write("Education "+employee.getEducations().get(0).getInstitution());
//        out.write("\nEmployee Count "+EmployeeService.getEmployeeService().getAllEmployees().size());
//        out.close();
//        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeServlet");
//		dispatcher.forward(request, response);
//        
        
    }

}
