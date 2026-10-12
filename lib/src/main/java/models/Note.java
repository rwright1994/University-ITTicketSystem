package models;

import java.util.Date;

public class Note {

	private String content;
	private final Date postedDate;
	
	
	public Note(String contet, Date postedDate) {
		this.content = content;
		this.postedDate = new Date();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	
}
