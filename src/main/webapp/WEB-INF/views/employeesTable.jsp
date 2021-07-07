<%@page import="java.util.List"%>
<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
<title>Employee Table</title>
</head>
<body>
<div class="bg-green-400 mx-auto max-w-screen-lg h-screen">

<div class="flex flex-col container max-w-screen-lg mx-auto bg-green-400 rounded-xl ">
<div class="flex mt-20 mx-auto" >
	<h1>
		Welcome to employee list 
	</h1>
	
</div>
<div class="flex mx-auto" >
	
	<br>Please select an employee to watch his/her resume
</div>
<div class="flex flex-row ml-10 mt-2 ">
 <% List<Employee> employees = (List<Employee>)request.getAttribute("employees"); %>
 <table class="table table-hover border-gray-400 border-4" border="3" style="width: 50%" height="50%">
  <thead>
   <tr>
    <th>ID</th>
    <th>Name</th>
    <td></td>
    <td></td>
   </tr>
  </thead>
  <tbody>
   <!--   for (Todo todo: todos) {  -->
   <% for(Employee employee : employees){ %>
   <tr>
    <td><%=employee.getId()%></td>
    <td><a href="ResumeServlet?id=<%=employee.getId()%>&operation=show"><%=employee.getFullName()%></a></td>
    <!-- <td><a href="EmployeeServlet?id=<%=employee.getId()%>&operation=delete">Delete</a></td> -->
    <td>
    	<form action="EmployeeServlet" method="post" >
		    <input type="hidden" name="id" value="<%=employee.getId()%>"/>       
		    <input type="hidden" name="operation" value="edit"/>
		    <button type="submit" class="btn btn-link">Edit</button>          
		</form>
    </td>
    <td>
    	<form action="EmployeeServlet" method="post" >
		    <input type="hidden" name="id" value="<%=employee.getId()%>"/>       
		    <input type="hidden" name="operation" value="delete"/>
		    
		    <!--
		    <button type="submit" class="btn btn-link" data-toggle="modal" data-target="#exampleModalCenter">Delete</button>
		    -->
		    <!-- Button trigger modal -->
			<button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
			  Delete
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">Are you Sure you want to delete?</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        You are about to delete employee resume of <%=employee.getFullName() %>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
			        <button type="submit" class="btn btn-danger">Delete</button>
			      </div>
			    </div>
			  </div>
			</div>   
			
		</form>
    </td>
    
   </tr>
   <%} %>
  </tbody>

 </table>
 
 </div>
 <div class="flex flex-row ml-10 mt-2 ">
 <a href = "CreateResumeServlet">
 	<button type="button" class="btn btn-primary">Add new Employee Resume</button>
 </a>
 	
 </div>
 </div>
 </div>
</body>
</html>