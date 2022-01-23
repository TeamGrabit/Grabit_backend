package grabit.grabit_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GrabitBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrabitBackendApplication.class, args);
	}

}
