package dao;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to interact with our user table, to login a user and get his info
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.log.Log;

import bean.LoginBean;
import utilities.TokenGenerator;

public class LoginService {
	
	private static void saveToken(LoginBean loginBean) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("UPDATE USERS SET token = ? WHERE username = ?");
			query.setString(1, loginBean.getToken());
			query.setString(2, loginBean.getUsername());
			
			query.executeUpdate();
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null)
				conn.close();
		}
	}

	public static LoginBean loginUser(String username, String password, boolean token) throws Exception{
		LoginBean user = new LoginBean();
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("SELECT * FROM USERS WHERE username = ? AND password = ?");
			query.setString(1, username);
			query.setString(2, password);
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				if (token) {
					user.setToken(TokenGenerator.generateToken(username));
					saveToken(user);
				}
				user.setResult("success");
			}
			if (user.getResult().equals("error"))
				user.setResult("loginPassword");
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
			user.setResult("databaseError");
		}
		finally {
			if (conn != null)
				conn.close();
		}
		return user;
	}
	
	public static LoginBean loginByToken(String username, String token) throws Exception {
		LoginBean user = new LoginBean();
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			conn = DatabaseAccess.connectDataBase();
			query = conn.prepareStatement("SELECT * FROM USERS WHERE username = ? AND token = ?");
			query.setString(1, username);
			query.setString(2, token);
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setToken(token);
				user.setResult("success");
			}
			if (user.getResult().equals("error"))
				user.setResult("loginPassword");
			
			query.close();
		} catch (Exception e) {
			e.printStackTrace();
			user.setResult("databaseError");
		}
		finally {
			if (conn != null)
				conn.close();
		}
		return user;
	}
}
