package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChallengeCustomRepository {

	Optional<Challenge> findChallengeById(Long id);
	List<Challenge> findAllChallengeWithPaging(Pageable pageable);
}
