package _ITTicketSystem.services;

public interface TerminalPolicy {

	void displayHelp();
	void displayFind();
	
	boolean isAuthorizedCommand(String command);
}
