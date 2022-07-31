package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.JoinChallengeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JoinChallengeRequestCustomRepository {
    Page<JoinChallengeRequest> findJoinChallengeRequestByChallengeWithPage(Pageable pageable, Challenge challenge);
}
