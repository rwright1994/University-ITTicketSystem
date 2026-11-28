package _ITTicketSystem.repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.util.Optional;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.Ticket;

public class InMemoryTicketRepository implements TicketRepository {

    private final Map<String, Ticket> ticketsById = new HashMap<>();

    @Override
    public Optional<Ticket> findTicketById(String id) {
        return Optional.ofNullable(ticketsById.get(id));
    }

    @Override
    public List<Ticket> findAllByUserId(String id) {
        return new ArrayList<>(ticketsById.values());
    }

    @Override
    public void save(Ticket ticket) {
        ticketsById.put(ticket.getTicketID(), ticket);
    }
    
	@Override
	public void findTicketAndUpdate(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void deleteById(String id) {
        ticketsById.remove(id);
    }

	@Override
	public List<Ticket> findAllTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextTicketId() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void addNote(String ticketId, Note note) {
		// TODO Auto-generated method stub
		
	}
}