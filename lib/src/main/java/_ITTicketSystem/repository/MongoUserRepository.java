package _ITTicketSystem.repository;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;

import _ITTicketSystem.models.Employee;
import _ITTicketSystem.models.Student;
import _ITTicketSystem.models.Technician;
import _ITTicketSystem.models.User;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserRepository implements UserRepository{

	private MongoCollection<Document> collection;
	private long nextId;
	
	public MongoUserRepository(MongoDatabase database) {
		this.collection = database.getCollection("users");
		this.collection.createIndex(
				Indexes.ascending("userId"), new IndexOptions().unique(true));
	}
	
	@Override
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		for(Document document: collection.find()) {
			User user = new User(
					document.getString("userId"),
					document.getString("firstName"),
					document.getString("lastName")
					);
			users.add(user);
		}
		return users;
	}

	@Override
	public Optional<User> findUserById(String id) {

	    Document found = collection.find(eq("userId", id)).first();

	    if (found == null) {
	        return Optional.empty();
	    }

	    User user = switch (found.getString("userType")) {
	        case "STUDENT" -> new Student(
	            found.getString("userId"),
	            found.getString("firstName"),
	            found.getString("lastName")
	        );
	        case "EMPLOYEE" -> new Employee(
	            found.getString("userId"),
	            found.getString("firstName"),
	            found.getString("lastName")
	        );
	        case "TECHNICIAN" -> new Technician(
	            found.getString("userId"),
	            found.getString("firstName"),
	            found.getString("lastName")
	        );

	        default -> throw new IllegalArgumentException("Unknown user type");
	    };

	    return Optional.of(user);
	}

	@Override
	public void save(User user) {

	    Document newUser = new Document()
	        .append("userId", user.getUserId())
	        .append("firstName", user.getFirstName())
	        .append("lastName", user.getLastName());

	    if (user instanceof Technician) {
	        newUser.append("userType", "TECHNICIAN");
	    }
	    else if (user instanceof Employee) {
	        newUser.append("userType", "EMPLOYEE");
	    }
	    else if (user instanceof Student) {
	        newUser.append("userType", "STUDENT");
	    }

	    collection.insertOne(newUser);
	}

	@Override
	public void deleteById(String id) {
		collection.deleteOne(eq("userId", id));
	}

	@Override
	public long getNextUserId() {
	    Document lastUser = collection.find()
	            .sort(Sorts.descending("userId"))
	            .first();
	    
	        if (lastUser == null) {
	            nextId = 0;
	        }else {
	        	nextId = Long.parseLong(lastUser.getString("userId"))+1;
	        }
		return nextId;
	}




}
