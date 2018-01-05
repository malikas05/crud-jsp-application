package dao;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used for connection to database
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAccess {
	
	private static String username = "admin";;
	private static String password = "admin";
	private static String database = "COMP3095";

	private static Connection connect = null;
	  
	public static Connection connectDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/"+database+"?"
							+ "user="+username+"&password="+password);
			return connect;
	    	} catch (Exception e) {
	    		throw e;
	    	} 
	}
}
