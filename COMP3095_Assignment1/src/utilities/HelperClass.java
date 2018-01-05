package utilities;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class is used to check the fields
 */

public class HelperClass {

	public static boolean checkField(String field) {
		return field.matches("[a-zA-Z ]+");
	}
	
	public static boolean checkInt(String field) {
		try {
			Integer.parseInt(field);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
