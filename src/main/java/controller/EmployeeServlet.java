package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeDao;
import model.Employee;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EmployeeService employeeService = EmployeeService.getEmployeeService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        this.employeeService = EmployeeService.getEmployeeService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameterMap().containsKey("operation"))
		{
			if(request.getParameter("operation").equals("delete"))
			{
				
				System.out.println("Deleting");
				
				Employee employee = EmployeeService.getEmployeeService().getEmployeeById(Integer.parseInt(request.getParameter("id")));
				EmployeeService.getEmployeeService().deleteEmployee(employee);
				
				request.setAttribute("employees",this.employeeService.getAllEmployees());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeesTable.jsp");
				dispatcher.forward(request, response);
			}
			if(request.getParameter("operation").equals("edit"))
			{
				
				System.out.println("Deleting");
				
				Employee employee = EmployeeService.getEmployeeService().getEmployeeById(Integer.parseInt(request.getParameter("id")));
				//EmployeeService.getEmployeeService().deleteEmployee(employee);
				
				
				request.setAttribute("employee",employee);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editResume.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
			request.setAttribute("employees",this.employeeService.getAllEmployees());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/employeesTable.jsp");
			dispatcher.forward(request, response);
		
		
	}

}
