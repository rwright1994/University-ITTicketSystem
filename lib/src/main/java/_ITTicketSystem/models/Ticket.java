
package _ITTicketSystem.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an individual support ticket in the IT ticketing system.
 *
 * <p>
 * A ticket tracks information about a reported issue, including its
 * identification, description, category, submission date, first response,
 * closing date, current status, associated notes, assigned technician,
 * and submitting user.
 * </p>
 *
 * <p>
 * Tickets are constructed using the {@link TicketBuilder} class and
 * manage their own life-cycle, including note management and closure.
 * </p>
 *
 * @author Robert Wright
 * @version 1.0
 * @since 26-10-12
 */
public class Ticket {

    // -------------------------------------------------------------------------
    // Ticket attributes
    // -------------------------------------------------------------------------

    /** Unique identifier for the ticket. */
    private final String ticketID;

    /** Title of the ticket. */
    private String title;

    /** Description of the reported issue. */
    private String description;

    /** Category assigned to the issue. */
    private TicketCategory category;

    /** Original submission date and time of the ticket. */
    private final LocalDateTime submissionDate;

    /** Date and time when the ticket received its first response. */
    private LocalDateTime firstResponseDate;

    /** Date and time when the ticket was closed. */
    private LocalDateTime closeDate;

    /** Current status of the ticket. */
    private TicketStatus status;

    /** Notes associated with the ticket. */
    private final List<Note> notes = new ArrayList<>();

    /** Identifier of the technician assigned to the ticket. */
    private String technicianID;

    /** Identifier of the user who submitted the ticket. */
    private final String userID;


    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    /**
     * Constructs a Ticket using the values configured in a TicketBuilder.
     *
     * <p>
     * This constructor initializes the ticket's attributes using the
     * builder, allowing both new tickets and previously persisted tickets
     * to be constructed.
     * </p>
     *
     * @param builder builder containing the ticket's initial attributes
     */
    public Ticket(TicketBuilder builder) {
        this.ticketID = builder.getTicketID();
        this.title = builder.getTitle();
        this.description = builder.getDescription();
        this.category = builder.getCategory();
        this.submissionDate = builder.getSubmissionDate();
        this.firstResponseDate = builder.getFirstResponseDate();
        this.closeDate = builder.getCloseDate();
        this.status = builder.getStatus();
        this.notes.addAll(builder.getNotes());
        this.technicianID = builder.getTechnicianID();
        this.userID = builder.getUserID();
    }


    // -------------------------------------------------------------------------
    // Lifecycle operations
    // -------------------------------------------------------------------------

    /**
     * Records the first response date and time for the ticket.
     *
     * <p>
     * The timestamp is only recorded once. Subsequent calls will not
     * overwrite the original first response date.
     * </p>
     */
    public void recordFirstResponse() {
        if (this.firstResponseDate == null) {
            this.firstResponseDate = LocalDateTime.now();
        }
    }

    /**
     * Closes the ticket and records its closing date and time.
     *
     * <p>
     * Updates the ticket status to {@link TicketStatus#CLOSED}.
     * </p>
     */
    public void closeTicket() {
        if (this.status != TicketStatus.CLOSED) {
            this.closeDate = LocalDateTime.now();
            this.status = TicketStatus.CLOSED;
        }
    }

    /**
     * Adds a new note to the ticket.
     *
     * @param newNote note to be added to the ticket
     */
    public void addNote(Note newNote) {
        this.notes.add(newNote);
    }


    // -------------------------------------------------------------------------
    // Time calculations
    // -------------------------------------------------------------------------

    /**
     * Calculates the time elapsed between ticket submission and its
     * first response.
     *
     * @return duration between submission and first response,
     *         or null if the ticket has not received a first response
     */
    public Duration getFirstResponseTime() {
        if (firstResponseDate == null) {
            return null;
        }

        return Duration.between(submissionDate, firstResponseDate);
    }

    /**
     * Calculates the time elapsed between ticket submission and closure.
     *
     * @return duration between submission and closure,
     *         or null if the ticket has not been closed
     */
    public Duration getResolutionTime() {
        if (closeDate == null) {
            return null;
        }

        return Duration.between(submissionDate, closeDate);
    }


    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /**
     * Returns the ticket's unique identifier.
     *
     * @return ticket identifier
     */
    public String getTicketID() {
        return ticketID;
    }

    /**
     * Returns the ticket title.
     *
     * @return ticket title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the ticket description.
     *
     * @return ticket description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the ticket category.
     *
     * @return ticket category
     */
    public TicketCategory getCategory() {
        return category;
    }

    /**
     * Returns the ticket submission date and time.
     *
     * @return submission date and time
     */
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Returns the ticket's first response date and time.
     *
     * @return first response date and time, or null if no response exists
     */
    public LocalDateTime getFirstResponseDate() {
        return firstResponseDate;
    }

    /**
     * Returns the ticket closing date and time.
     *
     * @return closing date and time, or null if the ticket is still open
     */
    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    /**
     * Returns the current ticket status.
     *
     * @return current ticket status
     */
    public TicketStatus getStatus() {
        return status;
    }

    /**
     * Returns the notes associated with the ticket.
     *
     * @return list of ticket notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Returns the identifier of the assigned technician.
     *
     * @return technician identifier, or null if unassigned
     */
    public String getTechnicianID() {
        return technicianID;
    }

    /**
     * Returns the identifier of the user who submitted the ticket.
     *
     * @return submitting user identifier
     */
    public String getUserID() {
        return userID;
    }


    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    /**
     * Updates the ticket title.
     *
     * @param title new ticket title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Updates the ticket description.
     *
     * @param description new ticket description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the ticket category.
     *
     * @param category new ticket category
     */
    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    /**
     * Updates the ticket status.
     *
     * @param status new ticket status
     */
    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    /**
     * Assigns a technician to the ticket.
     *
     * @param technicianID identifier of the technician
     */
    public void setTechnician(String technicianID) {
        this.technicianID = technicianID;
    }


    // -------------------------------------------------------------------------
    // Object methods
    // -------------------------------------------------------------------------

    /**
     * Returns a string representation of the ticket and its attributes.
     *
     * @return formatted string containing ticket information
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", submissionDate=" + submissionDate +
                ", firstResponseDate=" + firstResponseDate +
                ", closeDate=" + closeDate +
                ", status=" + status +
                ", technicianID=" + technicianID +
                ", userID=" + userID +
                '}';
    }
}
