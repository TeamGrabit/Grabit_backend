package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.JoinChallengeRequest;
import grabit.grabit_backend.domain.UserChallengePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinChallengeRequestRepository extends JpaRepository<JoinChallengeRequest, Long> {
    JoinChallengeRequest save(JoinChallengeRequest joinChallengeRequest);
    List<JoinChallengeRequest> findByChallenge(Challenge challenge);
}
