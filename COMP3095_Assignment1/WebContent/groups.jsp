<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Groups</title>
  <link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" href="resources/css/main.css" />
</head>

<body>
<%@page import="bean.DepartmentBean, data.DataLab, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%@ include file="menu.jsp" %>  

<%
	List<DepartmentBean> departments = DataLab.getInstance().getDepartmentsFromLab();
	request.setAttribute("departments", departments);
%>

<% String formVisibility = "visible";
	if (request.getAttribute("formVisibility") != null)
		formVisibility = "invisible";
	String responseVisibility = "invisible";
	if (request.getAttribute("responseVisibility") != null)
		responseVisibility = "visible";
	String oneEmp = "inner-hide";
	if (request.getAttribute("oneEmp") != null)
		oneEmp = request.getAttribute("oneEmp").toString();
	String uniqueEmp = "inner-hide";
	if (request.getAttribute("uniqueEmp") != null)
		uniqueEmp = request.getAttribute("uniqueEmp").toString();
%>

  <div class="container">
    <div id="main-content" class="row align-items-center">
      <div class="col"></div>
      <div class="col">

        <div class="card" style="width: 40rem;">
          <div class="card-body">
            <h4 class="card-title">Group Entry</h4>
            <form action="groupServlet" method="POST" class="<%= formVisibility %>">
              <div class="form-group">
                <label for="departmentName">Department</label>
                <select class="form-control ${isInvalidDep }" id="departmentName" name="departmentName" required>
                	 <c:forEach items="${departments}" var="u">  
                  <option value="${u.getId() }">${u.getName() }</option>
                  </c:forEach>  
                </select>
                <div class="invalid-feedback">
                  Please select a valid department.
                </div>
              </div>
              <div class="form-group">
                <label for="groupName">Group Name</label>
                <input type="text" class="form-control ${isInvalid}" id="groupName" name="groupName" aria-describedby="groupName" placeholder="Enter group name"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid group name.
                </div>
              </div>
              <div class="row">
                <div class="form-group col-4">
                  <label for="employee1">Employee 1</label>
                  <select class="form-control" id="employee1" name="employee1" required>
                  <option selected value="0">No Employee</option>
                    
                  </select>
                  <div class="invalid-feedback">
                  
                	</div>
                </div>
                <div class="form-group col-4">
                  <label for="employee2">Employee 2</label>
                  <select class="form-control ${isInvalidEmpIdentity }" id="employee2" name="employee2" required>
                    <option selected value="0">No Employee</option>
                    
                  </select>
                  <div class="invalid-feedback">
                    
                  </div>
                </div>
                <div class="form-group col-4">
                  <label for="employee3">Employee 3</label>
                  <select class="form-control" id="employee3" name="employee3" required>
                    <option selected value="0">No Employee</option>
                    
                  </select>
                  <div class="invalid-feedback">
                    <!-- Please select an employee. -->
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-4">
                  <label for="employee4">Employee 4</label>
                  <select class="form-control" id="employee4" name="employee4" required>
                    <option selected value="0">No Employee</option>
                    
                  </select>
                  <div class="invalid-feedback">
                    <!-- Please select an employee. -->
                  </div>
                </div>
                <div class="form-group col-4">
                  <label for="employee5">Employee 5</label>
                  <select class="form-control" id="employee5" name="employee5" required>
                    <option selected value="0">No Employee</option>
                    
                  </select>
                  <div class="invalid-feedback">
                    <!-- Please select an employee. -->
                  </div>
                </div>
                <div class="form-group col-4">
                  <label for="employee6">Employee 6</label>
                  <select class="form-control" id="employee6" name="employee6" required>
                    <option selected value="0">No Employee</option>
                    
                  </select>
                  <div class="invalid-feedback">
                    <!-- Please select an employee. -->
                  </div>
                </div>
                <div class="alert alert-danger <%= oneEmp %>" role="alert">
  					Please select at least one employee.
				</div>
				<div class="alert alert-danger <%= uniqueEmp %>" role="alert">
  					You can only choose unique employee in each select box.
				</div>
                
              </div>
              <button type="submit" class="btn btn-success">Submit</button>
              <button type="reset" class="btn btn-danger">Cancel</button>
            </form>
            <div class="alert alert-success <%= responseVisibility %>"" role="alert">
              Sucessfully created a group!
              <div class="alert alert-secondary" role="alert">
                Group "${groupName }" has been added to the system, it contains following employees:
                <ul>
                <c:forEach items="${empNames}" var="u">
                		<li>${u}</li>  
                 </c:forEach>  

                </ul>
              </div>
            </div>
          </div>
        </div>

      </div>
      <div class="col"></div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
</body>
<script type="text/javascript">
        
</script>
</html>