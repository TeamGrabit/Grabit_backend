package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChallengeCustomRepository {

	Optional<Challenge> findChallengeById(Long id);
	Page<Challenge> findChallengeBySearchWithPaging(Pageable pageable, String title, String description, String leaderId);
}
