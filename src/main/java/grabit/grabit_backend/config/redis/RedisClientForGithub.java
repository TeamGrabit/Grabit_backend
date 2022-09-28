package grabit.grabit_backend.config.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class RedisClientForGithub {

	@Value("${grabit.redis.uri}")
	private String redisURI;
	private RedisCommands<String, String> commands;

	public RedisClientForGithub() {}

	public RedisCommands<String, String> getCommands() {
		RedisClient client = RedisClient.create(redisURI);
		StatefulRedisConnection<String, String> connection = client.connect();
		this.commands = connection.sync();
		return this.commands;
	}
}
