package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChallengeCustomRepository {

	Optional<Challenge> findChallengeById(Long id);
	Page<Challenge> findAllChallengeWithPaging(Pageable pageable);
	Page<Challenge> findUserJoinedChallengeList(Pageable pageable, User user);
}
