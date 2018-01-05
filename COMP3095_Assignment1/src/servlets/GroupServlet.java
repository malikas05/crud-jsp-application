package servlets;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This servlet class is used to handle requests from the groups.jsp file
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeService;
import dao.GroupService;
import data.DataLab;
import utilities.HelperClass;

/**
 * Servlet implementation class GroupServlet
 */
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet() {
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
		String groupName = request.getParameter("groupName");
		int departmentID = Integer.parseInt(request.getParameter("departmentName"));
		int[] employees = new int[6];
		int k = 1;
		for (int i = 0; i < employees.length; i++) {
			employees[i] = Integer.parseInt(request.getParameter("employee" + k++));
		}
		boolean added = false;
		List<String> employeeNames = new ArrayList<>();
		Map<Integer, Integer> mapEmpIDsChosen = new HashMap<>();
		
		String errorMsgGroupName = HelperClass.checkField(groupName) ? "": "is-invalid";
		
		try {
			if (errorMsgGroupName.isEmpty()) {
				boolean canAddGroup = false;
				boolean checkEmpIdentity = true;
				for (int i = 0; i < employees.length; i++) {
					if (employees[i] != 0) {
						if (mapEmpIDsChosen.containsKey(employees[i]))
							mapEmpIDsChosen.put(employees[i], mapEmpIDsChosen.get(employees[i])+1);
						else
							mapEmpIDsChosen.put(employees[i], 1);
					}
					
					if (employees[i] > 0) {
						canAddGroup = true;
					}
				}
				
				for (Map.Entry<Integer, Integer> entry : mapEmpIDsChosen.entrySet())
				{
					if (entry.getValue() > 1) {
						checkEmpIdentity = false;
						break;
					}
				}
				
				if (canAddGroup && checkEmpIdentity) {
					int groupID = GroupService.addGroup(groupName, departmentID);
					for (int i = 0; i < employees.length; i++) {
						if (groupID > 0 && employees[i] > 0) {
							added = GroupService.addEmpToGroup(groupID, employees[i]);
							employeeNames.add(DataLab.getInstance().getEmpNameByID(employees[i]));
						}
					}
				}
				else if (!checkEmpIdentity) {
					request.setAttribute("uniqueEmp", "inner-show");
				}
				else {
					// no employee chosen
					request.setAttribute("oneEmp", "inner-show");
				}

				if (added) {
					request.setAttribute("isInvalid", errorMsgGroupName);
					request.setAttribute("oneEmp", "inner-hide");
					request.setAttribute("uniqueEmp", "inner-hide");
						
					request.setAttribute("responseVisibility", "visible");
					request.setAttribute("formVisibility", "invisible");
						
					request.setAttribute("groupName", groupName);
					request.setAttribute("empNames", employeeNames);
				}
			}
			else {
				request.setAttribute("isInvalid", errorMsgGroupName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("groups.jsp").forward(request, response);
	}

}
