<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Departments</title>
  <link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" href="resources/css/main.css" />
</head>

<body>
	<%@ include file="menu.jsp" %>  
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
            <h4 class="card-title">Department Entry</h4>
            <form action="depServlet" method="POST" class="<%= formVisibility %>">
              <div class="form-group">
                <label for="departmentName">Department Name</label>
                <input type="text" class="form-control" id="departmentName" name="departmentName" aria-describedby="departmentName" placeholder="Enter department name"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid department name.
                </div>
              </div>
              <div class="form-group">
                <label for="departmentLocation">Department Location/Floor</label>
                <input type="text" class="form-control" id="departmentLocation" name="departmentLocation" aria-describedby="departmentLocation" placeholder="Enter department location"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid department location.
                </div>
              </div>
              <button type="submit" class="btn btn-success">Submit</button>
              <button type="reset" class="btn btn-danger">Cancel</button>
            </form>
            <div class="alert alert-success <%= responseVisibility %>" role="alert">
              Sucessfully added department!
              <div class="alert alert-secondary" role="alert">
                department ${departmentName } (${departmentLocation}) has been added to the system
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