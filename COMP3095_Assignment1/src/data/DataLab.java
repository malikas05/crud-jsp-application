package data;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to store departments, positions and employees by department. The singleton pattern is used here 
 * in order to get access to our variables from any class of the application.
 */

import java.util.ArrayList;
import java.util.List;

import bean.DepartmentBean;
import bean.EmployeeBean;
import bean.PositionBean;
import dao.DepartmentsService;
import dao.EmployeeService;

public class DataLab {

	private static DataLab instance;
	private List<DepartmentBean> departments = new ArrayList<>();
	private List<PositionBean> positions = new ArrayList<>();
	private List<EmployeeBean> employeeDep = new ArrayList<>();
	
	public static DataLab getInstance() {
		if (instance == null)
			instance = new DataLab();
		return instance;
	}
	
	private DataLab() {
		try {
			departments = DepartmentsService.getAllDepartments();
			positions = DepartmentsService.getAllPositions();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<DepartmentBean> getDepartmentsFromLab() {
		return departments;
	}
	
	public void addDepartmentToLab(DepartmentBean bean) {
		departments.add(bean);
	}
	
	public int getLastId() {
		return departments.get(departments.size() - 1).getId();
	}
	
	public List<PositionBean> getPositionsFromLab() {
		return positions;
	}
	
	public List<EmployeeBean> getEmployeesByDep(int departmentID) {
		try {
			employeeDep = EmployeeService.getEmployeesByDepartment(departmentID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeDep;
	}
	
	public String getEmpNameByID(int id) {
		for (EmployeeBean employeeBean : employeeDep) {
			if (employeeBean.getId() == id)
				return employeeBean.getFirstname() + " " + employeeBean.getLastname();
		}
		return "";
	}
}
