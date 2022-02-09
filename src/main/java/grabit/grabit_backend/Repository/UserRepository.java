package grabit.grabit_backend.Repository;

import grabit.grabit_backend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User challenge);
    Optional<User> findById(Integer unique_id);
    List<User> findAll();
    void deleteById(Integer id);
}
