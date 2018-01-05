package servlets;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This servlet class is used to handle requests from the departments.jsp file
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DepartmentBean;
import dao.DepartmentsService;
import data.DataLab;

/**
 * Servlet implementation class DepartmentsServlet
 */
public class DepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentsServlet() {
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
		String departmentName = request.getParameter("departmentName");
		String departmentLocation = request.getParameter("departmentLocation");
		boolean added = false;
		
		try {
			added = DepartmentsService.createDep(departmentName, departmentLocation);
			if (added) {
				DepartmentBean departmentBean = new DepartmentBean();
				departmentBean.setId(DataLab.getInstance().getLastId() + 1);
				departmentBean.setName(departmentName);
				DataLab.getInstance().addDepartmentToLab(departmentBean);
				
				request.setAttribute("departmentName", departmentName);
				request.setAttribute("departmentLocation", departmentLocation);
				request.setAttribute("responseVisibility", "visible");
				request.setAttribute("formVisibility", "invisible");
				request.getRequestDispatcher("departments.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
