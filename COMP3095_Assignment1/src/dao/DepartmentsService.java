package dao;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to interact with our department and position tables, get positions and departments, and create a department
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.DepartmentBean;
import bean.LoginBean;
import bean.PositionBean;
import sun.security.timestamp.TSRequest;
import utilities.TokenGenerator;

public class DepartmentsService {
	
	public static List<PositionBean> getAllPositions() throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		List<PositionBean> positionList = null;
		PositionBean bean = null;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("SELECT id, name FROM POSITIONS");
			ResultSet rs = query.executeQuery();
			
			positionList = new ArrayList<>();
			while (rs.next()) {
				bean = new PositionBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				positionList.add(bean);
			}
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null)
				conn.close();
		}
		return positionList;
	}
	
	public static List<DepartmentBean> getAllDepartments() throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		List<DepartmentBean> departmentList = null;
		DepartmentBean bean = null;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("SELECT id, name FROM DEPARTMENTS");
			ResultSet rs = query.executeQuery();
			
			departmentList = new ArrayList<>();
			while (rs.next()) {
				bean = new DepartmentBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				departmentList.add(bean);
			}
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null)
				conn.close();
		}
		return departmentList;
	}

	public static boolean createDep(String name, String location) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		int i = -1;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("INSERT INTO DEPARTMENTS (name, location) VALUES (?, ?)");
			query.setString(1, name);
			query.setString(2, location);
			
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
