package services;

import models.Note;
import models.User;


public interface EligibilityPolicy {

	boolean isEligible(User user, Note note);
	
}
