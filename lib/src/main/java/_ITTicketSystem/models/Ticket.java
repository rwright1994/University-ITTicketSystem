package _ITTicketSystem.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



/**
 * Represents a Ticket in a IT ticketing system.
 * <p>
 * This class allows tracking of individual ticket details including the ticketID, 
 * title, description, submission date, closing date, status, a list of notes, a technician
 * and the user who made the submission.
 * </p>
 * 
 * @author Robert Wright
 * @version 1.0
 * @since 26-10-12
 */

public class Ticket {

	/** Unique identifier for the ticket. **/
	private final String ticketID;
	
	/** Title for the ticket. **/
	private String title;
	
	/** Description of the issue. **/
	private String description;
	
	/** Category of the issue. **/
	private TicketCategory category;
	
	/** The date when the ticket was submitted. **/
	private final LocalDateTime submissionDate;
	
	/** Closing date for the ticket. **/
	private LocalDateTime closeDate;
	
	/** Current status of the ticket. **/
	private TicketStatus status;
	
	/** A list of all notes attached to the ticket. **/
	private final List<Note> notes = new ArrayList<Note>();
	
	/** Identifies the technician that is working on the ticket. **/
	private Technician technician;
	
	/** Identifies the user who submitted the ticket. **/
	private User user;
	
	
	 /**
     * Constructs a new Ticket with a specified ticketID, title, description, submission date and
     * the user who created the ticket. It assigns State.NEW upon creation.
     * 
     * @param ticketID for the ticket.
     * @param title of the ticket.
     * @param description of the issue contained in the ticket.
     * @param user who created the ticket
     */
	public Ticket(String ticketID, String title, String description, TicketCategory category,User user) {
		this.ticketID = ticketID;
		this.title = title;
		this.description = description;
		this.category = category;
		this.submissionDate = LocalDateTime.now();
		this.closeDate = null;
		this.status = TicketStatus.NEW;
		this.technician = null;
		this.user = user;
	}
	
	public Ticket(String ticketID,String title,String description,TicketCategory category,LocalDateTime submissionDate,TicketStatus status) {
		    this.ticketID = ticketID;
		    this.title = title;
		    this.description = description;
		    this.category = category;
		    this.user = null;
		    this.submissionDate = submissionDate;
		    this.closeDate = null;
		    this.status = status;
		    this.technician = null;
		}
	
	/** Adds a new note to the list. **/
	public void addNote(Note newNote) {
		this.notes.add(newNote);
	}
	
	
// Getters && Setters
	
	public String getTicketID() {
		 return ticketID;
	 }

	public String getTitle() {
		return title;
	}

	 public void setTitle(String title) {
		 this.title = title;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }

	 public LocalDateTime getCloseDate() {
		 return closeDate;
	 }

	 public void setCloseDate(LocalDateTime closeDate) {
		 this.closeDate = closeDate;
	 }

	 public TicketStatus getStatus() {
		 return status;
	 }

	 public void setStatus(TicketStatus status) {
		 this.status = status;
	 }
	 
	 public List<Note> getNotes(){
		 return this.notes;
	 }
	 
	 public void setCategory(TicketCategory newCategory) {
		 this.category = newCategory;
	 }
	 
	 public TicketCategory getCategory() {
		 return this.category;
	 }

	public User getUser() {
		return user;
	 }
	 
	/** Returns the submission date for the ticket. **/
	public LocalDateTime getSubmissionDate() {
		return this.submissionDate;
	}
	
	/** Returns the closing date of the ticket. **/
	public LocalDateTime getClosingDate() {
		return this.closeDate;
	}
		
	/** Returns the technician for the ticket. **/
	public Technician getTechnician() {
		return this.technician;
	}
		
	/** Sets current technician for the ticket. **/
	public void setTechnician(Technician technician) {
		this.technician = technician;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        """
	        --------------------------------------------------------------------------------
	    		           			TICKET DETAILS
	        --------------------------------------------------------------------------------
	        Ticket ID:       %s
	        Title:           %s
	        Description:     %s
	        Category:        %s
	        Status:          %s
	        Submitted:       %s
	        Closed:          %s
	        Submitted By:    %s
	        Assigned To:     %s
	        --------------------------------------------------------------------------------
	        """,
	        ticketID,
	        title,
	        description,
	        category,
	        status,
	        submissionDate,
	        closeDate == null ? "Not closed" : closeDate,
	        user == null ? "Unknown" : user.getFirstName() + " " + user.getLastName(),
	        technician == null ? "Unassigned" :
	            technician.getFirstName() + " " + technician.getLastName()
	    );
	}
}
