
package _ITTicketSystem.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder responsible for constructing {@link Ticket} objects.
 *
 * Provides a fluent interface for setting ticket attributes before
 * creating a Ticket instance. It can also be used to reconstruct
 * existing tickets from persisted data.
 */
public class TicketBuilder {

    // -------------------------------------------------------------------------
    // Ticket attributes
    // -------------------------------------------------------------------------

    /** Unique identifier for the ticket. */
    private String ticketID;

    /** Title of the ticket. */
    private String title;

    /** Description of the reported issue. */
    private String description;

    /** Category assigned to the issue. */
    private TicketCategory category;

    /** Date and time when the ticket was submitted. */
    private LocalDateTime submissionDate;

    /** Date and time when the ticket received its first response. */
    private LocalDateTime firstResponseDate;

    /** Date and time when the ticket was closed. */
    private LocalDateTime closeDate;

    /** Current status of the ticket. */
    private TicketStatus status;

    /** Notes associated with the ticket. */
    private List<Note> notes = new ArrayList<>();

    /** Identifier of the technician assigned to the ticket. */
    private String technicianID;

    /** Identifier of the user who submitted the ticket. */
    private String userID;


    // -------------------------------------------------------------------------
    // Fluent builder methods
    // -------------------------------------------------------------------------

    /**
     * Sets the ticket's unique identifier.
     *
     * @param ticketID unique ticket identifier
     * @return this builder instance
     */
    public TicketBuilder ticketID(String ticketID) {
        this.ticketID = ticketID;
        return this;
    }

    /**
     * Sets the ticket title.
     *
     * @param title title of the ticket
     * @return this builder instance
     */
    public TicketBuilder title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the ticket description.
     *
     * @param description description of the reported issue
     * @return this builder instance
     */
    public TicketBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the category of the ticket.
     *
     * @param category category assigned to the issue
     * @return this builder instance
     */
    public TicketBuilder category(TicketCategory category) {
        this.category = category;
        return this;
    }

    /**
     * Sets the ticket submission date and time.
     *
     * @param submissionDate original submission date and time
     * @return this builder instance
     */
    public TicketBuilder submissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
        return this;
    }

    /**
     * Sets the ticket's first response date and time.
     *
     * @param firstResponseDate date and time of the first response
     * @return this builder instance
     */
    public TicketBuilder firstResponseDate(LocalDateTime firstResponseDate) {
        this.firstResponseDate = firstResponseDate;
        return this;
    }

    /**
     * Sets the ticket closing date and time.
     *
     * @param closeDate date and time when the ticket was closed
     * @return this builder instance
     */
    public TicketBuilder closeDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    /**
     * Sets the current ticket status.
     *
     * @param status current status of the ticket
     * @return this builder instance
     */
    public TicketBuilder status(TicketStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the notes associated with the ticket.
     *
     * @param notes list of ticket notes
     * @return this builder instance
     */
    public TicketBuilder notes(List<Note> notes) {
        this.notes = notes;
        return this;
    }

    /**
     * Sets the identifier of the assigned technician.
     *
     * @param technicianID identifier of the technician
     * @return this builder instance
     */
    public TicketBuilder technician(String technicianID) {
        this.technicianID = technicianID;
        return this;
    }

    /**
     * Sets the identifier of the user who submitted the ticket.
     *
     * @param userID identifier of the submitting user
     * @return this builder instance
     */
    public TicketBuilder userID(String userID) {
        this.userID = userID;
        return this;
    }


    // -------------------------------------------------------------------------
    // Build method
    // -------------------------------------------------------------------------

    /**
     * Constructs a Ticket using the values configured in this builder.
     *
     * @return newly constructed Ticket instance
     */
    public Ticket build() {
        return new Ticket(this);
    }


    // -------------------------------------------------------------------------
    // Accessors used by Ticket
    // -------------------------------------------------------------------------

    /** @return the ticket identifier */
    public String getTicketID() {
        return ticketID;
    }

    /** @return the ticket title */
    public String getTitle() {
        return title;
    }

    /** @return the ticket description */
    public String getDescription() {
        return description;
    }

    /** @return the ticket category */
    public TicketCategory getCategory() {
        return category;
    }

    /** @return the ticket submission date and time */
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    /** @return the ticket's first response date and time */
    public LocalDateTime getFirstResponseDate() {
        return firstResponseDate;
    }

    /** @return the ticket closing date and time */
    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    /** @return the current ticket status */
    public TicketStatus getStatus() {
        return status;
    }

    /** @return the notes associated with the ticket */
    public List<Note> getNotes() {
        return notes;
    }

    /** @return the identifier of the assigned technician */
    public String getTechnicianID() {
        return technicianID;
    }

    /** @return the identifier of the submitting user */
    public String getUserID() {
        return userID;
    }
}
