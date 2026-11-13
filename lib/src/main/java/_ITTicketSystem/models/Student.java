package _ITTicketSystem.models;

public class Student extends User{
	
	private boolean isEnrolled;

	public Student(String userID, String firstName, String lastName) {
		super(userID, firstName, lastName);
		this.isEnrolled = true;
	}
	
	private boolean getEnrollmentStatus() {
		return isEnrolled;
	}
	
	private boolean changeEnrollementStatus() {
		this.isEnrolled = !this.isEnrolled;
		return this.isEnrolled;
	}
}
