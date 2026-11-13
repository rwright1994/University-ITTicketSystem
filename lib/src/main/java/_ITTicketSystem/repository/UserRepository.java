package _ITTicketSystem.repository;

import java.util.List;
import java.util.Optional;

import _ITTicketSystem.models.User;

public interface UserRepository {

	List<User> findAllUsers();
	Optional<User> findUserById(String id);
	
	void save(User user);
	
	void deleteById(String id);
	
	public long getNextUserId();
	
	
}
