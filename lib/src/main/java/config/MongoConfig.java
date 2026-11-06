package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MongoConfig {

	private final ConnectionString connectionString;
	private final String databaseName;
	
	private MongoConfig(ConnectionString connectionString, String databaseName) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
	}
	
	
	 /**
     * Loads the MongoDB URI and database name from the external properties file.
     *
     * Example (credentials are the student's own Atlas database user):
     * change this "@cluster0.cu9qeko.mongodb.net" to your own uri
     *
     * @param configFile path to mongodb.properties (supplied by the composition root)
     * @return configured MongoConfig object
     * @throws IOException if the file cannot be read
     * @throws IllegalStateException if a setting is missing, unedited, or invalid
     */
    public static MongoConfig load(Path configFile) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(configFile)) {
            properties.load(input);
        }

        String uri = required(properties, "mongodb.uri");
        String database = required(properties, "mongodb.database");

        if (uri.contains("<username>") || uri.contains("<password>")) {
            throw new IllegalStateException(
                    "Edit config/mongodb.properties: replace <username> and <password> "
                    + "with your own MongoDB Atlas database user.");
        }

        ConnectionString connectionString;
        try {
            // The driver parses the complete URI, including the credentials it contains.
            connectionString = new ConnectionString(uri);
        } catch (IllegalArgumentException ex) {
            // Do not echo the URI: it contains a password.
            throw new IllegalStateException(
                    "mongodb.uri in config/mongodb.properties is not a valid MongoDB connection string. "
                    + "Check the format and percent-encode special characters in the password.");
        }
        return new MongoConfig(connectionString, database);
    }
    
    /**
     * Creates a MongoDB client. The server selection timeout prevents the application
     * from waiting indefinitely while looking for an available MongoDB server.
     */
    public MongoClient connect() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToClusterSettings(builder ->
                        builder.serverSelectionTimeout(10, TimeUnit.SECONDS))
                .build();
        return MongoClients.create(settings);
    }

    /** @return MongoDB database name */
    public String databaseName() {
        return databaseName;
    }
    
    /** Reads a required property and checks that it is not missing or blank. */
    private static String required(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required configuration: " + key);
        }
        return value.trim();
    }
}
