package _ITTicketSystem.services;

import java.util.List;
import java.util.Optional;

import _ITTicketSystem.models.Employee;
import _ITTicketSystem.models.Student;
import _ITTicketSystem.models.Technician;
import _ITTicketSystem.models.User;
import _ITTicketSystem.repository.UserRepository;

public class UserCreationService {

	private UserRepository repository;
	private long nextId = 300000000;
	
	public UserCreationService(UserRepository repository) {
		this.repository = repository;
		long repositoryId =repository.getNextUserId();
		if(repository.getNextUserId() != 0) nextId = repositoryId;
	}
	
	public User createStudent(String fn, String ln) {
		User newStudent = new Student(generateUserId(),fn,ln);
		return newStudent;
	}
	
	public User createEmployee(String fn, String ln) {
		User newEmployee = new Employee(generateUserId(),fn,ln);
		return newEmployee;
	}
	
	public User createTechnician(String fn, String ln) {
		User newTechnician = new Technician(generateUserId(),fn,ln);
		
		return newTechnician;
	}
	

	public void save(User user) {
		repository.save(user);
	}
	
	public List<User> findAllUsers(){
		return repository.findAllUsers();
	}
	
	
	public Optional<User> findUserById(String ticketId) {
		return repository.findUserById(ticketId);
	}
	
	private String generateUserId() {
		return String.valueOf(nextId++);
	}

	
}
