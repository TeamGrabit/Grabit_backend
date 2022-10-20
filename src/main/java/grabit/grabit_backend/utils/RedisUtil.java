package grabit.grabit_backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisUtil {

	private final RedisTemplate<String, String> redisTemplate;

	public RedisUtil(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public <T> boolean saveData(String key, T data) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String value = objectMapper.writeValueAsString(data);
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			System.out.println("Redis save Error!!");
			e.printStackTrace();
			return false;
		}
	}

	public <T> Optional<T> getData(String key, Class<T> classType) {
		String value = redisTemplate.opsForValue().get(key);

		if (value == null) {
			return Optional.empty();
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return Optional.of(objectMapper.readValue(value, classType));
		} catch (Exception e) {
			System.out.println("Redis get Error!!");
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
