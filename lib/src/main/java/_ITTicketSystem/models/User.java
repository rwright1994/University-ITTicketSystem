package _ITTicketSystem.models;

public class User {

	private final String userID;
	private String firstName;
	private String lastName;

	
	public User(String userID, String firstName, String lastName) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUserId() {
		return this.userID;
	}
	
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public String toString() {
	    return "User{id=" + userID + ", firstName='" + firstName + "', lastName='" + lastName + "}";
	}
	

}
