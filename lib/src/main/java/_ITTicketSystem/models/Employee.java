package _ITTicketSystem.models;

public class Employee extends User {

	private final String position;
	
	public Employee(String userID, String firstName, String lastName) {
		super(userID, firstName,lastName);
		this.position = null;
	}

	public String getPosition() {
		return position;
	}
	
}
