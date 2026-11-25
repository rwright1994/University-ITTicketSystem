package _ITTicketSystem.utils;

import java.time.format.DateTimeFormatter;
import java.util.List;

import _ITTicketSystem.models.Ticket;
import _ITTicketSystem.models.User;
import _ITTicketSystem.services.TerminalPolicy;

public class TerminalTicketFormatter implements TerminalFormatter<Ticket> {

	
	@Override
	public String format(List<Ticket> tickets) {

	    StringBuilder output = new StringBuilder();
	    String format = "| %-9s | %-50s | %-19s | %-12s |%n";
	    output.append("+-----------+----------------------------------------------------+---------------------+--------------+\n");
	    output.append(String.format(format,
	            "Ticket ID", "Title", "Submission Date", "Status"));
	    output.append("+-----------+----------------------------------------------------+---------------------+--------------+\n");
	    for (Ticket ticket : tickets) {
	        output.append(String.format(format,
	                ticket.getTicketID(),
	                ticket.getTitle().substring(0, Math.min(ticket.getTitle().length(), 50)),
	                ticket.getSubmissionDate().format(
	                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
	                ),
	                ticket.getStatus()
	        ));
	    }
	    output.append("+-----------+----------------------------------------------------+---------------------+--------------+\n");
	    return output.toString();
	}
	
	public String format(Ticket ticket) {

	    StringBuilder output = new StringBuilder();

	    output.append(String.format(
	            """
	            +-------------------------------------------------------------------------------+
	                                        TICKET DETAILS
	            +-------------------------------------------------------------------------------+
	            Ticket ID:       %s
	            Title:           %s
	            Description:     """,
	            ticket.getTicketID(),
	            ticket.getTitle()
	    ));


	    appendWrappedDescription(output, ticket.getDescription(),64);

	    output.append(String.format(
	            """
	            Category:        %s
	            Status:          %s
	            Submitted:       %s
	            Closed:          %s
	            Submitted By:    %s
	            Assigned To:     %s
	            +-------------------------------------------------------------------------------+
	            """,
	            ticket.getCategory(),
	            ticket.getStatus(),
	            ticket.getSubmissionDate(),
	            ticket.getCloseDate() == null ? "Not closed" : ticket.getCloseDate(),
	            ticket.getUser() == null ? "Unknown" :
	                    ticket.getUser().getFirstName() + " " + ticket.getUser().getLastName(),
	            ticket.getTechnician() == null ? "Unassigned" :
	                    ticket.getTechnician().getFirstName() + " " +
	                    ticket.getTechnician().getLastName()
	    ));

	    return output.toString();
	}
	
	private void appendWrappedDescription(StringBuilder output, String description, int width) {

	    int start = 0;

	    while (start < description.length()) {
	        int end = Math.min(start + width, description.length());
	        if (end < description.length()) {
	            int lastSpace = description.lastIndexOf(" ", end);
	            if (lastSpace > start) {
	                end = lastSpace;
	            }
	        }

	        if (start > 0) {
	            output.append("                 ");
	        }

	        output.append(description.substring(start, end).trim()).append("\n");

	        start = end;

	        while (start < description.length() && description.charAt(start) == ' ') {
	            start++;
	        }
	    }
	}
	

}
