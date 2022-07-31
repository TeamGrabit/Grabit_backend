package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.JoinChallengeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinChallengeRequestRepository extends JpaRepository<JoinChallengeRequest, Long>, JoinChallengeRequestCustomRepository {
    JoinChallengeRequest save(JoinChallengeRequest joinChallengeRequest);
    List<JoinChallengeRequest> findJoinChallengeRequestsByChallenge(Challenge challenge);
    void delete(JoinChallengeRequest joinChallengeRequest);
}
