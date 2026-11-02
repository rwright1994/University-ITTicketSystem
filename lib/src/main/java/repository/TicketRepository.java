package repository;

import com.google.common.base.Optional;

import models.Ticket;

public interface TicketRepository {
	 
	public Optional<Ticket> findTicketById(int id);
	
	void save(Ticket ticket);
	
	void deleteById(int id);
	
}
