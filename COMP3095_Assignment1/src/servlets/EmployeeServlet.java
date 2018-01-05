package servlets;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This servlet class is used to handle requests from the employees.jsp file
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentsService;
import dao.EmployeeService;
import data.DataLab;
import utilities.HelperClass;

/**
 * Servlet implementation class EmployeeServlet
 */

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String employeeNumber = request.getParameter("employeeNumber");
		String email = request.getParameter("email");
		String hireYear = request.getParameter("hireYear");
		String jobPositionID = request.getParameter("jobPosition");
		boolean added = false;
		
		String errorMsgFirstName = HelperClass.checkField(firstname) ? "": "is-invalid";
		String errorMsgLastName = HelperClass.checkField(lastname) ? "": "is-invalid";
		String errorEmployeeNumber = HelperClass.checkInt(employeeNumber) ? "": "is-invalid";
		String errorEmail = "";
		String errorHireYear = Integer.parseInt(hireYear) >= 2010 ? "" : "is-invalid";
		
		try {
			if (errorMsgFirstName.isEmpty() && errorMsgFirstName.isEmpty() && errorEmployeeNumber.isEmpty() && errorEmail.isEmpty() &&
					errorHireYear.isEmpty()) {
				added = EmployeeService.addEmployee(firstname, lastname, Integer.parseInt(employeeNumber), email, 
						Integer.parseInt(hireYear), Integer.parseInt(jobPositionID));
				if (added) {
					request.setAttribute("isInvalidFirst", errorMsgFirstName);
					request.setAttribute("isInvalidLast", errorMsgLastName);
					request.setAttribute("isInvalidEmp", errorEmployeeNumber);
					request.setAttribute("isInvalidEmail", errorEmail);
					request.setAttribute("isInvalidHireYear", errorHireYear);
					
					request.setAttribute("responseVisibility", "visible");
					request.setAttribute("formVisibility", "invisible");
					
					request.setAttribute("firstname", firstname);
					request.setAttribute("lastname", lastname);
				}
			}
			else {
				request.setAttribute("isInvalidFirst", errorMsgFirstName);
				request.setAttribute("isInvalidLast", errorMsgLastName);
				request.setAttribute("isInvalidEmp", errorEmployeeNumber);
				request.setAttribute("isInvalidEmail", errorEmail);
				request.setAttribute("isInvalidHireYear", errorHireYear);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("employees.jsp").forward(request, response);
	}

}
