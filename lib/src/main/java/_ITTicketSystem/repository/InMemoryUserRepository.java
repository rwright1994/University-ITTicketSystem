package _ITTicketSystem.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import _ITTicketSystem.models.User;

public class InMemoryUserRepository implements UserRepository{
	
	private final Map<String, User> usersById = new HashMap();
	
	public Optional<User> findUserById(String id){
		return Optional.ofNullable(usersById.get(id));
	}
	
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save(User user) {
		usersById.put(user.getUserId(), user);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public long getNextUserId() {
		// TODO Auto-generated method stub
		return 0;
	}


}
