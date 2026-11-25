package _ITTicketSystem.utils;

import java.util.List;

import _ITTicketSystem.models.User;

public class TerminalUserFormatter implements TerminalFormatter<User> {

	@Override
	public String format(User user) {
		return null;
	}
	
	@Override
	public String format(List<User> users) {

	    StringBuilder output = new StringBuilder();

	    String format = "| %-10s | %-20s | %-20s |%n";
	    output.append("+------------+----------------------+----------------------+\n");
	    output.append(String.format(format,	"User ID", "First Name", "Last Name"));
	    output.append("+------------+----------------------+----------------------+\n");

	    for (User user : users) {
	        output.append(String.format(format,
	            user.getUserId(),
	            user.getFirstName(),
	            user.getLastName()
	        ));
	    }
	    output.append("+------------+----------------------+----------------------+\n");

	    return output.toString();
	
	}	
}