package connectors;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public class DatabaseConnector extends AbstractMongoClientConfiguration{

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Bean
    @Override
    public MongoClient mongoClient() {
    	
    	String uri = System.getenv("MONGODB_URI");
    
    	if (uri == null || uri.isBlank()) {
    		throw new IllegalStateException("MONGODB_URI environment variable is not set. Please refer to readme.md.");
    	}
    	
    	
    	System.out.println();
        return MongoClients.create(
            System.getenv("mongodb+srv://ticketeer_dbreader:6TsKDCmqiYCnADuO@the-university-ticketee.zgbx5tm.mongodb.net/")
        );
    }
    
   
}
