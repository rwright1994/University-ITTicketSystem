package _ITTicketSystem.services;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.User;


public interface EligibilityPolicy {

	boolean isEligible(User user, Note note);
	
}
