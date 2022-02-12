package grabit.grabit_backend.Service;

import grabit.grabit_backend.DTO.CreateChallengeDTO;
import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

	private final ChallengeRepository challengeRepository;

	@Autowired
	public ChallengeService(ChallengeRepository challengeRepository){
		this.challengeRepository = challengeRepository;
	}

	/**
	 * 챌린지 생성
	 * @param createChallengeDTO
	 * @return id
	 */
	public Long createChallenge(CreateChallengeDTO createChallengeDTO){
		Challenge challenge = new Challenge("testId",createChallengeDTO.getName(), createChallengeDTO.getChallengeDesc());
		// 유저 아이디 정보 확인.
		return challengeRepository.save(challenge).getId();
	}

	/**
	 * 챌린지 검색 (id)
	 * @param id
	 * @return Challenge
	 */
	public Challenge findChallengeById(Long id){
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		return findChallenge.get();
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
		// 유저 아이디 정보 확인.
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		challengeRepository.deleteById(id);
	}

	/**
	 * 챌린지 수정
	 * @param id
	 * @param afterChallenge
	 * @return Challenge
	 */
	public Challenge updateChallenge(Long id, Challenge afterChallenge){
		// 유저 아이디 정보 확인.
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		findChallenge.get().setName(afterChallenge.getName());
		findChallenge.get().setLeaderId(afterChallenge.getLeaderId());
		return challengeRepository.save(findChallenge.get());
	}

	/**
	 * 모든 챌린지 검색
	 * @return List of Challenge
	 */
	public List<Challenge> findAllChallenge(){
		return challengeRepository.findAll();
	}

}
