package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import grabit.grabit_backend.domain.UserChallengePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, UserChallengePK> {
	UserChallenge save(UserChallenge userChallenge);
	Optional<UserChallenge> findByUserAndChallenge(User user, Challenge challenge);
	void deleteByUserAndChallenge(User user, Challenge challenge);
	void deleteAllByUserAndChallenge(User user, Challenge challenge);
}
