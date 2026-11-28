package _ITTicketSystem.utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.NoteVisibility;
import _ITTicketSystem.services.UserTerminalPolicy;
import _ITTicketSystem.services.TechnicianTerminalPolicy;
import _ITTicketSystem.services.TerminalPolicy;


public class TerminalNoteFormatter implements TerminalFormatter<Note> {

	private final TerminalPolicy tp;
	
	public TerminalNoteFormatter(TerminalPolicy tp) {
		this.tp = tp;
	}
	
	
	@Override
	public String format(Note note) {
		return null;
	}
	
	@Override
    public String format(List<Note> notes) {

        StringBuilder output = new StringBuilder();
        output.append("+-------------------------------------------------------------------------------+\n");
        output.append("	           		 TICKET NOTES\n");
        output.append("+-------------------------------------------------------------------------------+\n");
        
        
        for (Note note : notes) {
        	if(tp.canViewNote(note)) {
	            output.append("+-------------------------------------------------------------------------------+\n");
	
	            output.append(String.format("| Posted By:  %s%n", note.getUser() == null ? "Unknown" : note.getUser().getFirstName() + " " +note.getUser().getLastName()));
	            output.append(String.format("| Date:       %s%n", note.getPostedDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"))));
	
	            String content = note.getContent();
	            int contentWidth = 64;
	
	            for (int i = 0; i < content.length(); i += contentWidth) {
	                int end = Math.min(i + contentWidth, content.length());
	                output.append(i == 0 ? "| Content:    " : "|             ");
	                output.append(content, i, end).append("\n");
	            }
	            output.append("+-------------------------------------------------------------------------------+\n");
        	}
        }
        return output.toString();
    }

}
