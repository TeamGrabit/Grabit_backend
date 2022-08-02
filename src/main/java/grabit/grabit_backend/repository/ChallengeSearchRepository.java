package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChallengeSearchRepository {
	Page<Challenge> findChallengeWithPaing(Pageable pageable, String content);
}
