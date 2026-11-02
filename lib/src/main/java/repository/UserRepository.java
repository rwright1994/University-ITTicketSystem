package repository;

import com.google.common.base.Optional;

import models.User;

public interface UserRepository {
;
	public Optional<User> findUserById(int id);
	
	void save(User user);
	void deleteById(int id);
	
	
}
