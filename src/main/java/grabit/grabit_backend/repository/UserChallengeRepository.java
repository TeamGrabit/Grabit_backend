package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
	UserChallenge save(UserChallenge userChallenge);
	void deleteByUserAndChallenge(User user, Challenge challenge);
}
