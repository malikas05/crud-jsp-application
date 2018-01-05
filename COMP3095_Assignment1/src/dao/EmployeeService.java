package dao;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to interact with our employee table, add employee and get employees by department
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.EmployeeBean;
import bean.PositionBean;

public class EmployeeService {
	
	public static List<EmployeeBean> getEmployeesByDepartment(int departmentID) throws Exception {
			
			PreparedStatement query = null;
			Connection conn = null;
			List<EmployeeBean> employeeList = null;
			EmployeeBean bean = null;
			
			try {
				conn = DatabaseAccess.connectDataBase();
				query = conn.prepareStatement("SELECT e.id, e.firstname, e.lastname FROM EMPLOYEE e "
						+ "INNER JOIN POSITIONS p ON "
						+ "e.jobPositionID = p.id "
						+ "WHERE p.departmentID = ?");
				query.setInt(1, departmentID);
				ResultSet rs = query.executeQuery();
				
				employeeList = new ArrayList<>();
				while (rs.next()) {
					bean = new EmployeeBean();
					bean.setId(rs.getInt("id"));
					bean.setFirstname(rs.getString("firstname"));
					bean.setLastname(rs.getString("lastname"));
					employeeList.add(bean);
				}
				
				query.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (conn != null)
					conn.close();
			}
			return employeeList;
	}

	public static boolean addEmployee(String firstname, String lastname, int employeeNum,
			String email, int hireYear, int jobPositionID) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		int i = -1;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("INSERT INTO EMPLOYEE (firstname, lastname, email, "
					+ "number, hireYear, jobPositionID) VALUES (?, ?, ?, ?, ?, ?)");
			query.setString(1, firstname);
			query.setString(2, lastname);
			query.setString(3, email);
			query.setInt(4, employeeNum);
			query.setInt(5, hireYear);
			query.setInt(6, jobPositionID);
			
			i = query.executeUpdate();
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if (conn != null)
				conn.close();
		}
		return i > 0;
	}
}
