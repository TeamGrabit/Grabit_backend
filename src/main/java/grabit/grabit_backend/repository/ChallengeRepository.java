package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query("select c from challenge c join fetch c.userChallengeList uc join fetch uc.user where c.id = ?1")
	Optional<Challenge> findById(Long id);

	@Query("select distinct c from challenge c join fetch c.userChallengeList uc")
	List<Challenge> findAllWithPaging(Pageable pageable);
}
