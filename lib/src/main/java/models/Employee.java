package models;

public class Employee extends User {

	private final int employeeID;
	private final String position;
	
	public Employee(int userID, String firstName, String lastName, String email, String password, int employeeID) {
		super(userID, firstName,lastName,email, password);
		this.employeeID = employeeID;
		this.position = null;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getPosition() {
		return position;
	}
	
}
