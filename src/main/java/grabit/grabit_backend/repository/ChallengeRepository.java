package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	Challenge save(Challenge challenge);
	List<Challenge> findByName(String name);
	void deleteById(Long id);
	Optional<Challenge> findById(Long id);

	@Query("select distinct c from challenge c join fetch c.userChallengeList")
	List<Challenge> findAll();
}
