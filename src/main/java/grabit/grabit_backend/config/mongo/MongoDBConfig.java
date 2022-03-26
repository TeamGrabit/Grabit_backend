package grabit.grabit_backend.config.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

	@Value("${mongodbURI}")
	private String uri;

	@Bean
	public MongoClient mongoClient(){
		return MongoClients.create(uri);
	}
}
