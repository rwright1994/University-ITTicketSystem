package _ITTicketSystem.utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

import _ITTicketSystem.models.Ticket;
import _ITTicketSystem.models.User;

public class TerminalTicketFormatter implements TerminalFormatter<Ticket> {

	@Override
	public String format(List<Ticket> tickets) {
	    StringBuilder output = new StringBuilder();

	    String format = "| %-9s | %-30s | %-19s | %-12s |%n";

	    output.append("+-----------+--------------------------------+---------------------+--------------+\n");
	    
	    output.append(String.format(format,
	            "Ticket ID", "Title", "Submission Date", "Status"));

	        output.append("+-----------+--------------------------------+---------------------+--------------+\n");

	        for (Ticket ticket : tickets) {
	            output.append(String.format(format,
	                ticket.getTicketID(),
	                ticket.getTitle(),
	                ticket.getSubmissionDate().format(
	                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
	                ),
	                ticket.getStatus()
	            ));
	        }

	        output.append("+-----------+--------------------------------+---------------------+--------------+\n");

	        return output.toString();
	}


	
	
}
