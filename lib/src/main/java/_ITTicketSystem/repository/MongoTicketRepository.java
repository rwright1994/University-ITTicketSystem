package _ITTicketSystem.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;

import static com.mongodb.client.model.Filters.eq;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.NoteVisibility;
import _ITTicketSystem.models.Ticket;
import _ITTicketSystem.models.TicketBuilder;
import _ITTicketSystem.models.TicketCategory;
import _ITTicketSystem.models.TicketStatus;
import _ITTicketSystem.models.User;




public class MongoTicketRepository implements TicketRepository{
	
	private final MongoCollection<Document> collection;
	private final UserRepository userRepository;
	private int nextId;
	
	
	public MongoTicketRepository(MongoDatabase database, UserRepository userRepository) {
		this.collection = database.getCollection("tickets");
		this.collection.createIndex(
				Indexes.ascending("ticketId"),
                new IndexOptions().unique(true));
		this.userRepository = userRepository;
	}
	
	
	@Override
	public void save(Ticket ticket) {
		Document newTicket = new Document();
		newTicket.append("ticketId", ticket.getTicketID())
		.append("title", ticket.getTitle())
		.append("description",ticket.getDescription())
		.append("ticketCategory", ticket.getCategory())
		.append("submissionDate", ticket.getSubmissionDate())
		.append("status", ticket.getStatus())
		.append("userId", ticket.getUserID());
		
		collection.insertOne(newTicket);
	}
	
	@Override
	public List<Ticket> findAllTickets() {
	    List<Ticket> tickets = new ArrayList<>();
	    for (Document document : collection.find()) {
	    	Ticket ticket = new TicketBuilder()
	    			.ticketID(document.getString("ticketId"))
	    			.title(document.getString("title"))
	    			.description(document.getString("description"))
					.category(TicketCategory.valueOf(document.getString("ticketCategory")))
					.submissionDate(convertToLocalDateTime(document.getDate("submissionDate")))
					.status(TicketStatus.valueOf(document.getString("status")))
					.userID(document.getString("userId"))
	    			.build();
			
	        tickets.add(ticket);
	    }

	    return tickets;
	}

 
	@Override
	public void deleteById(String id) {
		this.collection.deleteOne(eq("ticketId", id));
		
	}

	@Override
	public Optional<Ticket> findTicketById(String id) {
		
		Document found = collection.find(eq("ticketId", id)).first();
		User user = userRepository.findUserById(found.getString("userId")).orElse(null);
	
		
		LocalDateTime submissionDate = convertToLocalDateTime(found.getDate("submissionDate"));
		
		
		Ticket ticket = new TicketBuilder()
				.ticketID(found.getString("ticketId"))
				.title(found.getString("title"))
				.description(found.getString("description"))
				.category(TicketCategory.valueOf(found.getString("ticketCategory")))
				.submissionDate(submissionDate)
				.status(TicketStatus.valueOf(found.getString("status")))
				.build();
		
		List<Document>notes = found.getList("notes",Document.class);
		if(notes != null) {
			for(Document note:notes) {
				ticket.addNote(new Note(
						note.getString("noteId"),
						note.getString("content"),
						user,
						NoteVisibility.valueOf(note.getString("visibility"))));
			}
		}

		return Optional.ofNullable(ticket);
	}

	@Override
	public List<Ticket> findAllByUserId(String id) {
		List<Document> users = collection.find(eq("userId",id)).into(new ArrayList<>());
		return null;
	}
	
	public int getNextTicketId() {
	    Document lastTicket = collection.find()
	            .sort(Sorts.descending("ticketId"))
	            .first();
	    
	        if (lastTicket == null) {
	            nextId = 0;
	        }else {
	        	nextId = Integer.parseInt(lastTicket.getString("ticketId"))+1;
	        }
		return nextId;
	}

	@Override
	public void findTicketAndUpdate(Ticket ticket) {
		
		Document updatedTicket = new Document()
				.append("ticketId",ticket.getTicketID())
				.append("title", ticket.getTitle())
				.append("description",ticket.getDescription())
				.append("ticketCategory", ticket.getCategory())
				.append("submissionDate", ticket.getSubmissionDate())
				.append("closeDate", ticket.getCloseDate())
				.append("status", ticket.getStatus())
				.append("technician", ticket.getTechnicianID())
				.append("userId", ticket.getUserID());
				
		collection.findOneAndReplace(
				Filters.eq("ticketId", ticket.getTicketID()),
				updatedTicket);
	}

	@Override
	public void addNote(String ticketId, Note note) {

	    Document noteDocument = new Document()
	    	.append("noteId", note.getNoteId())
	        .append("content", note.getContent())
	        .append("userId", note.getUser().getUserId())
	        .append("postedDate", note.getPostedDate())
	        .append("visibility", note.getNoteVisiblity().toString());
	    	

	    collection.updateOne(
	        Filters.eq("ticketId", ticketId),
	        Updates.push("notes", noteDocument)
	    );
	}
	
	private LocalDateTime convertToLocalDateTime(Date date) {
		LocalDateTime submission = date.toInstant()
		    .atZone(ZoneId.systemDefault())
		    .toLocalDateTime();
		
		return submission;
	}
	
	
}



