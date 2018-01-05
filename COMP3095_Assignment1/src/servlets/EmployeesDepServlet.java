package servlets;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This servlet class is used to refresh departments data of the select box 
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EmployeeBean;
import data.DataLab;

import com.google.gson.Gson;

/**
 * Servlet implementation class EmployeesDep
 */
public class EmployeesDepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesDepServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isInvalidDep", "");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson data = new Gson();
		
		String departmentIDString = request.getParameter("departmentID");
		List<EmployeeBean> employees = null;
		int departmentID = 0;
		try {
			departmentID = Integer.parseInt(departmentIDString);
			employees = DataLab.getInstance().getEmployeesByDep(departmentID);
		} catch (Exception e) {
			// Invalid departmentID
			request.setAttribute("isInvalidDep", "is-invalid");
			request.getRequestDispatcher("groups.jsp").forward(request, response);
		}
		if (employees != null && !employees.isEmpty()) {
			String ajaxData = data.toJson(employees);
			response.getWriter().write(ajaxData);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
