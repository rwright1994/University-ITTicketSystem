package models;

public class Technician extends User{

	
	private boolean isEmployee;
	private final String employeeID;
	
	public Technician(int userID, String firstName, String lastName, String email, String employeeID, String password) {
		super(userID, firstName, lastName, email, password);
		this.employeeID = employeeID;
		this.isEmployee = true;
	}
	
	public boolean getEmploymentStatus() {
		return this.isEmployee;
	}
	
	public String getEmployeeID() {
		return this.employeeID;
	}
	
	
}
