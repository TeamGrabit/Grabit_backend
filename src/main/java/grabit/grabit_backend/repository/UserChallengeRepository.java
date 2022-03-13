package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
	UserChallenge save(UserChallenge userChallenge);
}
