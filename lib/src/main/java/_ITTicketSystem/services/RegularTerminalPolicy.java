package _ITTicketSystem.services;

public class RegularTerminalPolicy implements TerminalPolicy {

	private static final String[] regularCommands = {"help","submit-ticket","add-note","view","find", "help","exit", "ticket", "all-tickets", "add-note"};
	
	@Override
	public void displayHelp() {
		System.out.println("------------------------ Available Commands -------------------------");
		System.out.println("  submit-ticket  - Submit a new ticket.");
		System.out.println("  add-note       - add a new note to a ticket.");
		System.out.println("  view           - view all of your current tickets.");
		System.out.println("  help           - Show available commands.");
		System.out.println("  exit           - Exit application.");
		System.out.println("---------------------------------------------------------------------");
	}
	
	@Override
	public void displayFind() {
		System.out.println("------------------------ Search Options ------------------------------");
		System.out.println("ticket        - find a ticket (by ID).");
		System.out.println("all-tickets   - lists all tickets sorted by ticket id.");
		System.out.println("exit          - leave current menu");
		System.out.println("---------------------------------------------------------------------");
	}
	
	
	@Override
	public boolean isAuthorizedCommand(String command) {
		for(int i=0; i < regularCommands.length; i++) {
			if(command.equalsIgnoreCase(regularCommands[i])) {
				return true;
			}
		}
		return false;
	}
	
	

}
