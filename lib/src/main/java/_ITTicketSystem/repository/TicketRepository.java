package _ITTicketSystem.repository;

import java.util.List;

import java.util.Optional;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.Ticket;

public interface TicketRepository {
	 
	List<Ticket> findAllTickets();
	
	Optional<Ticket> findTicketById(String id);
	
	List<Ticket> findAllByUserId(String id);
	
	void findTicketAndUpdate(Ticket ticket);
	
	void save(Ticket ticket);
	
	void addNote(String ticketId, Note note);
	
	void deleteById(String id);
	
	public int getNextTicketId();
	
	
	
}
