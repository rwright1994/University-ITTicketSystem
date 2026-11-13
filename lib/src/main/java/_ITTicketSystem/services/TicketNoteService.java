
package _ITTicketSystem.services;

import java.util.Optional;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.NoteVisibility;
import _ITTicketSystem.models.Ticket;
import _ITTicketSystem.models.User;
import _ITTicketSystem.repository.TicketRepository;

/**
 * Provides business operations for creating and adding notes to tickets.
 * Coordinates note creation and ticket validation with the ticket repository.
 */
public class TicketNoteService {

    private final TicketRepository ticketRepository;

    /**
     * Constructs a TicketNoteService using the supplied ticket repository.
     *
     * @param ticketRepository the repository used to retrieve and update tickets.
     */
    public TicketNoteService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Creates a new note with the specified content, author, and visibility.
     *
     * @param content the text content of the note.
     * @param user the user creating the note.
     * @param visibility the visibility level assigned to the note.
     * @return the newly created Note.
     */
    public Note create(String noteId, String content, User user, NoteVisibility visibility) {
        return new Note(noteId, content, user, visibility);
    }

    /**
     * Adds a note to an existing ticket and persists the update.
     *
     * @param ticketId the ID of the ticket receiving the note.
     * @param note the note being added to the ticket.
     * @throws IllegalArgumentException if no ticket exists with the supplied ID.
     */
	public void addNote(String ticketId, Note note) {
		Optional<Ticket> ticket =ticketRepository.findTicketById(ticketId);

		if (ticket.isEmpty())throw new IllegalArgumentException("Ticket not found");
		ticketRepository.addNote(ticketId, note);
	}
	
	public void findAllNotes(String ticketId) {
		Optional<Ticket> ticket = ticketRepository.findTicketById(ticketId);
		

	}
	
}
