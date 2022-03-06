package grabit.grabit_backend.Repository;

import grabit.grabit_backend.Domain.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
	UserChallenge save(UserChallenge userChallenge);
}
