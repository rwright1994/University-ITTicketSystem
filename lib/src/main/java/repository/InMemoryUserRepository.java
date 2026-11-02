package repository;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import models.User;

public class InMemoryUserRepository implements UserRepository{
	
	private final Map<Integer, User> usersById = new HashMap();
	
	public Optional<User> findUserById(int id){
		return Optional.fromNullable(usersById.get(id));
	}
	
	public void save(User user) {
		usersById.put(user.getUserId(), user);
	}
	
	public void deleteById(int id) {
		
		usersById.remove(id);
	}
}
