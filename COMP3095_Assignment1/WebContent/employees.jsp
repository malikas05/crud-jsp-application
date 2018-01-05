<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Employees</title>
  <link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" href="resources/css/main.css" />
</head>

<body>
<%@page import="bean.PositionBean, data.DataLab, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%@ include file="menu.jsp" %>

<%
	List<PositionBean> positions = DataLab.getInstance().getPositionsFromLab();
	request.setAttribute("positions", positions);
%>  

<% String formVisibility = "visible";
	if (request.getAttribute("formVisibility") != null)
		formVisibility = "invisible";
	String responseVisibility = "invisible";
	if (request.getAttribute("responseVisibility") != null)
		responseVisibility = "visible";
%>


  <div class="container">
    <div id="main-content" class="row align-items-center">
      <div class="col"></div>
      <div class="col">

        <div class="card" style="width: 40rem;">
          <div class="card-body">
            <h4 class="card-title">Employee Entry</h4>
            <form action="empServlet" method="POST" class="<%= formVisibility %>">
              <div class="form-group">
                <label for="firstname">Firstname</label>
                <input type="text" class="form-control ${isInvalidFirst}" id="firstname" name="firstname" aria-describedby="firstname" placeholder="Enter firstname"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid firstname.
                </div>
              </div>
              <div class="form-group">
                <label for="lastname">Lastname</label>
                <input type="text" class="form-control ${isInvalidLast}" id="lastname" name="lastname" aria-describedby="lastname" placeholder="Enter lastname"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid lastname.
                </div>
              </div>
              <div class="form-group">
                <label for="employeeNumber">Employee #</label>
                <input type="number" class="form-control ${isInvalidEmp}" id="employeeNumber" name="employeeNumber" aria-describedby="employeeNumber" placeholder="Enter employee #"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid employee #.
                </div>
              </div>
              <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control ${isInvalidEmail}" id="email" name="email" aria-describedby="email" placeholder="Enter email" required>
                <div class="invalid-feedback">
                  Please provide a valid email.
                </div>
              </div>
              <div class="form-group">
                <select class="form-control ${isInvalidHireYear}" id="hireYear" name="hireYear" required>
                  <option selected value="0">HireYear</option>
                  <option value="2010">2010</option>
                  <option value="2011">2011</option>
                  <option value="2012">2012</option>
                  <option value="2013">2013</option>
                  <option value="2014">2014</option>
                  <option value="2015">2015</option>
                  <option value="2016">2016</option>
                  <option value="2017">2017</option>
                </select>
                <div class="invalid-feedback">
                  Please provide a valid hire year.
                </div>
              </div>
              <div class="form-group">
                <select class="form-control" id="jobPosition" name="jobPosition" required>
                  <c:forEach items="${positions}" var="u">
                  <option value="${u.getId() }">${u.getName() }</option>
					</c:forEach>  
                </select>
                <div class="invalid-feedback">
                  Please provide a valid job position.
                </div>
              </div>
              <button type="submit" class="btn btn-success">Submit</button>
              <button type="reset" class="btn btn-danger">Cancel</button>
            </form>
            <div class="alert alert-success <%= responseVisibility %>" role="alert">
              Sucessfully added employee!
              <div class="alert alert-secondary" role="alert">
                ${firstname } ${lastname } has been added to the system
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
</html>