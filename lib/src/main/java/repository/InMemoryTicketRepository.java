package repository;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import models.Ticket;

public class InMemoryTicketRepository implements TicketRepository {
	
	private final Map<Integer, Ticket> ticketsById = new HashMap();

	@Override
	public void save(Ticket ticket) {
			ticketsById.put(ticket.getTicketID(), ticket);
	}

	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		ticketsById.remove(id);
	}

	@Override
	public Optional<Ticket> findTicketById(int id) {
		return Optional.fromNullable(ticketsById.get(id));
		
	}

}
