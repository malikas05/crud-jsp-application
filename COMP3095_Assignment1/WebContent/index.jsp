<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Please Authenticate Yourself</title>
  <link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" href="resources/css/main.css" />
</head>

<body>
<%
	String authentication = "inner-hide";
	if (request.getAttribute("authentication") != null)
		authentication = request.getAttribute("authentication").toString();
%>

  <div class="container">
    <div id="login" class="row align-items-center">
      <div class="col"></div>
      <div class="col">

        <div class="card" style="width: 20rem;">
          <div class="card-body">
            <h4 class="card-title">Login</h4>
            <form action="loginServlet" method="POST">
              <div class="form-group">
                <label for="loginUsername">Username</label>
                <input type="text" class="form-control ${isInvalid}" id="loginUsername" name="loginUsername" aria-describedby="loginUsername" placeholder="Enter username"
                  required>
                <div class="invalid-feedback">
                  Please provide a valid username.
                </div>
              </div>
              <div class="form-group">
                <label for="loginPassword">Password</label>
                <input type="password" class="form-control ${isInvalid}" id="loginPassword" name="loginPassword" placeholder="Password" required>
                <div class="invalid-feedback">
                  Please provide a valid password.
                </div>
              </div>
              <div class="form-check">
                <label class="form-check-label">
                  <input type="checkbox" class="form-check-input" id="rememeberMe" name="rememeberMe"> Remember me
                </label>
              </div>
              <div class="alert alert-danger <%= authentication %>" role="alert">
  					Please authenticate yourself first.
				</div>
              <button type="submit" class="btn btn-primary">Login</button>
            </form>
          </div>
        </div>

      </div>
      <div class="col"></div>
    </div>
  </div>
</body>

</html>