package models;

import java.util.Date;

public class Ticket {

	private String title;
	private String description;
	private Date submissionDate;
	private Date closeDate;
	private User technician;
	
	public Ticket(String title, String description, Date submissionDate) {
		this.title = title;
		this.description = description;
		this.submissionDate = submissionDate;
	}
	
}
