package dao;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to interact with our group table, add a group in the table and add employees to the group
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utilities.TokenGenerator;

public class GroupService {

	public static boolean addEmpToGroup(int groupID, int empID) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		int i = -1;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("INSERT INTO GROUP_EMP (groupID, empID) VALUES (?, ?)");
			query.setInt(1, groupID);
			query.setInt(2, empID);
			
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
	
	public static int addGroup(String name, int departmentID) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		int i = -1;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("INSERT INTO GROUP_T (name, departmentID) VALUES (?, ?)");
			query.setString(1, name);
			query.setInt(2, departmentID);
			
			i = query.executeUpdate();
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			if (conn != null)
				conn.close();
		}
		if (i > 0) {
			int groupLastID = getGroupLastID();
			if (groupLastID > 0)
				return groupLastID;
			else {
				return -1;
			}
		}
		else {
			return i;
		}
	}
	
	private static int getGroupLastID() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		int groupLastID = -1;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("SELECT id FROM GROUP_T ORDER BY id DESC LIMIT 1");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				groupLastID = rs.getInt("id");
				System.out.println("groupLastID " + groupLastID);
			}
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
			groupLastID = -1;
		}
		finally {
			if (conn != null)
				conn.close();
		}
		return groupLastID;
	}
}
