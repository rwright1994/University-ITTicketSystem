package services;

import models.User;

public class UserCreationService {

	private int nextId = 300000;
	
	public User createUser(String fn, String ln, String email, String pw) {
		User newUser = new User(nextId,fn,ln,email,pw);
		nextId = nextId++;
		return newUser;
	}
}
