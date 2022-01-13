package grabit.grabit_backend.Service;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

	@Autowired
	private ChallengeRepository challengeRepository;

	public Long makeChallenge(Challenge challenge){
		challengeRepository.save(challenge);
		return challenge.getId();
	}
}
