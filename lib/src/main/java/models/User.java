package models;

public class User {

	private String firstName;
	private String lastName;
	private String email;
	private String password;

	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
}
