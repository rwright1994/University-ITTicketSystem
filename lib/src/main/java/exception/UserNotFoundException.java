package exception;

public class UserNotFoundException extends RuntimeException {

	private final String role;
	private final int userID;
	
	public UserNotFoundException(String role, int userID) {
		super(String.format("Unable to locate a %s valid ID %i", role, userID));
		this.role = role;
		this.userID = userID;
	}
}
