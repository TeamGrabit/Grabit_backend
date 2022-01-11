package grabit.grabit_backend;

import grabit.grabit_backend.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class GrabitBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrabitBackendApplication.class, args);


	}

}
