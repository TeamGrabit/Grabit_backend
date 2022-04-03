package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	Challenge save(Challenge challenge);
	Optional<Challenge> findById(Long id);
	List<Challenge> findByName(String name);
	List<Challenge> findAll();
	void deleteById(Long id);
	Page<Challenge> findAll(Pageable pageable);
}
