package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User user);
    Optional<User> findById(Integer Id);
    Optional<User> findByUserId(String userId);
    List<User> findAll();
    void deleteById(Integer id);
}
