package models;

public class User {

	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	
	public User(int userID, String firstName, String lastName, String email, String password) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password; // Needs to be encrypted properly when written to the database.
	}
	
	public int getUserId() {
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
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean setPassword(String newPassword) {
		if(newPassword.length() < 8) {
			return false;
		}else {
			this.password = newPassword;
			return true;
		}
	}
	
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String toString() {
	    return "User{id=" + userID + ", firstName='" + firstName + "', lastName='" + lastName + "', email='" + email + "'}";
	}
}
