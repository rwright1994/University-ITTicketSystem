package _ITTicketSystem.services;

import _ITTicketSystem.models.Note;

public interface TerminalPolicy {

	void displayHelp();
	void displayFind();
	
	boolean isAuthorizedCommand(String command);
	boolean canViewNote(Note note);
}
