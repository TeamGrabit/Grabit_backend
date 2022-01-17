package grabit.grabit_backend.Service;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

	@Autowired
	private ChallengeRepository challengeRepository;

	/**
	 * 챌린지 생성
	 * @param challenge
	 * @return id
	 */
	public Long makeChallenge(Challenge challenge){
		return challengeRepository.save(challenge).getId();
	}

	/**
	 * 챌린지 검색 (id)
	 * @param id
	 * @return Optional Challenge
	 */
	public Optional<Challenge> findChallengeById(Long id){
		return challengeRepository.findById(id);
	}

	/**
	 * 챌린지 검색 (name)
	 * @param name
	 * @return List of Challenge
	 */
	public List<Challenge> findChallengeByName(String name){
		return challengeRepository.findByName(name);
	}

	/**
	 * 챌린지 삭제
	 * @param id
	 */
	public void deleteChallengeById(Long id){
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(!findChallenge.isPresent()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		challengeRepository.deleteById(id);
	}

}
