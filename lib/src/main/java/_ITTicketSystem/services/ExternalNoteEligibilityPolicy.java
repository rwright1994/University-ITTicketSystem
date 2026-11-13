package _ITTicketSystem.services;

import _ITTicketSystem.models.Note;
import _ITTicketSystem.models.User;

public class ExternalNoteEligibilityPolicy implements EligibilityPolicy {

	@Override
	public boolean isEligible(User user, Note note) {
		// TODO Auto-generated method stub
		return false;
	}

}
