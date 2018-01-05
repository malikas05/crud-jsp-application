package bean;

/**
 * Project: COMP3095_Malik_Max_Osman_Team
 * Assignment: 1
 * Author(s): Malik Iavari, Maxim Bondarenko, Osman Mammadli
 * Student Number: 101043865, 100420936, 100896285 
 * Date: 24/10/2017
 * Description: This class describes the company position object
 */

public class PositionBean {

	private int id;
	private String name;
	private int departmentID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
}
