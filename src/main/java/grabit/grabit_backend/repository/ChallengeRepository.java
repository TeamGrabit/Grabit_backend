package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>, ChallengeCustomRepository {
	Challenge save(Challenge challenge);
	List<Challenge> findByName(String name);
	void deleteById(Long id);

}
