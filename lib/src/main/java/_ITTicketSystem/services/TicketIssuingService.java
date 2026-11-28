package _ITTicketSystem.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import _ITTicketSystem.models.Technician;
import _ITTicketSystem.models.Ticket;
import _ITTicketSystem.models.TicketBuilder;
import _ITTicketSystem.models.TicketCategory;
import _ITTicketSystem.models.TicketStatus;
import _ITTicketSystem.models.User;
import _ITTicketSystem.repository.TicketRepository;

public class TicketIssuingService {

	private final TicketRepository repository;
	private int nextId = 1;
	
	public TicketIssuingService(TicketRepository repository) {
		this.repository = repository;
		if(repository.getNextTicketId() != 0) this.nextId = repository.getNextTicketId();
	}
	
	
	public Ticket issueTicket(String title, String description, TicketCategory category, String userID) {
		Ticket ticket = new TicketBuilder()
				.ticketID(String.valueOf(nextId))
				.title(title)
				.description(description)
				.category(category)
				.status(TicketStatus.NEW)
				.submissionDate(LocalDateTime.now())
				.userID(userID)
				.build();
		nextId++;
		return ticket;
	}
	
	public List<Ticket> findAllTickets(){
		return repository.findAllTickets();
	}
	
	public void save(Ticket ticket) {
		repository.save(ticket);
	}
	
	public void update(Ticket ticket) {
		repository.findTicketAndUpdate(ticket);
	}
	
	public Ticket delete(String ticketId){
		return null;
	}
	
	public void assignUser(Ticket ticket, User user) {
	 
	}
	
	public void assignTechnician(Technician technician) {
		
	}
	
	public Optional<Ticket> findTicketById(String ticketId){
		return repository.findTicketById(ticketId);
	}
	
}
