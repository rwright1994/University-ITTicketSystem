package _ITTicketSystem.services;

import _ITTicketSystem.models.Note;

public class TechnicianTerminalPolicy implements TerminalPolicy {
	
	private static final String[] technicianCommands = {"help","create-account","submit-ticket","assign-ticket","find","view","exit","ticket", "all-tickets", "user", "all-user", "add-note", "close-ticket"};

	@Override
	public void displayHelp() {
		System.out.println("+----------------------------- Available Commands ------------------------------+");
		System.out.println("  create-account - Register a new user.");
		System.out.println("  assign-ticket  - assign a ticket technician.");
		System.out.println("  submit-ticket  - Submit a new ticket.");
		System.out.println("  close-ticket   - Close a ticket by ID.");
		System.out.println("  find           - Find a ticket or user.");
		System.out.println("  view           - view all tickets.");
		System.out.println("  help           - Show available commands.");
		System.out.println("  exit           - Exit application.");
		System.out.println("+-------------------------------------------------------------------------------+");
	}
	
	@Override
	public void displayFind() {
		System.out.println("+---------------------------- Search Options -----------------------------------+");
		System.out.println("ticket        - find a ticket (by ID).");
		System.out.println("all-tickets   - lists all tickets sorted by ticket id.");
		System.out.println("user          - find a user (by ID).");
		System.out.println("all-users     - lists all users sorted by last name");
		System.out.println("exit          - leave current menu");
		System.out.println("+-------------------------------------------------------------------------------+");
	}
	

	@Override
	public boolean isAuthorizedCommand(String command) {
		for(int i=0; i < technicianCommands.length; i++) {
			if(command.equalsIgnoreCase(technicianCommands[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canViewNote(Note note) {
		return true;
	}
	
}
