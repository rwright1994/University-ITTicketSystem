package services;

import models.Note;
import models.User;

public class ExternalNoteEligibilityPolicy implements EligibilityPolicy {

	@Override
	public boolean isEligible(User user, Note note) {
		// TODO Auto-generated method stub
		return false;
	}

}
