package _ITTicketSystem;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MongoConnectionTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testMongoURI() {
        String uri = System.getenv("MONGODB_URI");
        assertNotNull(uri, "MONGODB_URI environment variable must be set");
    }

    @Test
    void testMongoConnection() {
        assertNotNull(mongoTemplate, "MongoTemplate should be initialized");
        assertNotNull(mongoTemplate.getDb(), "MongoDB database should be available");

        mongoTemplate.getDb().runCommand(new Document("ping", 1));

        System.out.println("MongoDB Atlas connection successful!");
    }
}