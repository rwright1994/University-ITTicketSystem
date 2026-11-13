package _ITTicketSystem.models;

public class Technician extends Employee{

	
	private boolean isEmployee;
	
	public Technician(String userID, String firstName, String lastName) {
		super(userID, firstName, lastName);
		this.isEmployee = true;
	}
	
	public boolean getEmploymentStatus() {
		return this.isEmployee;
	}
}
