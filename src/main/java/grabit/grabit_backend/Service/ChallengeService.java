package grabit.grabit_backend.Service;

import grabit.grabit_backend.DTO.CreateChallengeDTO;
import grabit.grabit_backend.DTO.ModifyChallengeDTO;
import grabit.grabit_backend.DTO.ResponseChallengeDTO;
import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Domain.User;
<<<<<<< HEAD
import grabit.grabit_backend.Repository.ChallengeRepository;
import grabit.grabit_backend.exception.UnauthorizedException;
=======
import grabit.grabit_backend.Domain.UserChallenge;
import grabit.grabit_backend.Repository.ChallengeRepository;
import grabit.grabit_backend.Repository.UserChallengeRepository;
>>>>>>> develop
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ChallengeService {

	private final ChallengeRepository challengeRepository;
	private final UserChallengeRepository userChallengeRepository;

	@Autowired
	public ChallengeService(ChallengeRepository challengeRepository, UserChallengeRepository userChallengeRepository){
		this.challengeRepository = challengeRepository;
		this.userChallengeRepository = userChallengeRepository;
	}

	/**
	 * 챌린지 생성
	 * @param createChallengeDTO
	 * @return id
	 */
<<<<<<< HEAD
	public ResponseChallengeDTO createChallenge(CreateChallengeDTO createChallengeDTO, User user){
		Challenge challenge = new Challenge(createChallengeDTO.getName(), createChallengeDTO.getDescription(),
				user.getUserId(), createChallengeDTO.getIsPrivate());
=======
	@Transactional
	public ResponseChallengeDTO createChallenge(CreateChallengeDTO createChallengeDTO, User leader){
		Challenge challenge = new Challenge(leader,createChallengeDTO.getName(), createChallengeDTO.getChallengeDesc());
>>>>>>> develop
		Challenge createChallenge = challengeRepository.save(challenge);
		// 유저 아이디 정보 확인.
		UserChallenge userChallenge = new UserChallenge();
		userChallenge.setChallenge(createChallenge);
		userChallenge.setUser(leader);
		userChallengeRepository.save(userChallenge);
		return ResponseChallengeDTO.convertDTO(createChallenge);
	}

	/**
	 * 챌린지 검색 (id)
	 * @param id
	 * @return Challenge
	 */
	public ResponseChallengeDTO findChallengeById(Long id){
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		return ResponseChallengeDTO.convertDTO(findChallenge.get());
	}

	/**
	 * 챌린지 검색 (name)
	 * @param name
	 * @return List of Challenge
	 */
	public ArrayList<ResponseChallengeDTO> findChallengeByName(String name){
		List<Challenge> findChallenges = challengeRepository.findByName(name);
		ArrayList<ResponseChallengeDTO> returnChallenges = new ArrayList<>();
		for(Challenge challenge: findChallenges){
			returnChallenges.add(ResponseChallengeDTO.convertDTO(challenge));
		}
		return returnChallenges;
	}

	/**
	 * 챌린지 삭제
	 * @param id
	 */
	public void deleteChallengeById(Long id, User user){
		Challenge findChallenge = isExistChallenge(id);

		// leader 여부 확인.
		if(findChallenge.getLeader() != user.getUserId()){
			throw new UnauthorizedException();
		}
		challengeRepository.deleteById(id);
	}

	/**
	 * 챌린지 수정
	 * @param id
	 * @param afterChallenge
	 * @return Challenge
	 */
	public ResponseChallengeDTO updateChallenge(Long id, ModifyChallengeDTO modifyChallengeDTO, User user){
		Challenge findChallenge = isExistChallenge(id);

		// leader 여부 확인.
		if(findChallenge.getLeader() != user.getUserId()){
			throw new UnauthorizedException();
		}
<<<<<<< HEAD

		findChallenge.modifyChallenge(modifyChallengeDTO);
		Challenge modifiedChallenge = challengeRepository.save(findChallenge);
		return ResponseChallengeDTO.convertDTO(modifiedChallenge);
=======
		findChallenge.get().setName(modifyChallengeDTO.getName());
//		findChallenge.get().setLeaderId(modifyChallengeDTO.getLeaderId());
		findChallenge.get().setChallengeDesc(modifyChallengeDTO.getChallengeDesc());
		Challenge modifyChallenge = challengeRepository.save(findChallenge.get());
		return ResponseChallengeDTO.convertDTO(modifyChallenge);
>>>>>>> develop
	}

	/**
	 * 모든 챌린지 검색
	 * @return ArrayList of ResponseChallengeDTO
	 */
	public ArrayList<ResponseChallengeDTO> findAllChallenge(){
		List<Challenge> findChallenges = challengeRepository.findAll();
		ArrayList<ResponseChallengeDTO> returnChallenges = new ArrayList<>();
		for(Challenge challenge: findChallenges){
			returnChallenges.add(ResponseChallengeDTO.convertDTO(challenge));
		}
		return returnChallenges;
	}

	/**
	 * 챌린지 가입
	 * @param id
	 * @param user
	 * @return
	 */
	public ResponseChallengeDTO joinChallenge(Long id, User user){
		Challenge findChallenge = isExistChallenge(id);

		Challenge modifiedChallenge = challengeRepository.save(findChallenge);
		return ResponseChallengeDTO.convertDTO(modifiedChallenge);
	}

	/**
	 * 챌린지 탈퇴
	 * @param id
	 * @param user
	 * @return
	 */
	public ResponseChallengeDTO leaveChallenge(Long id, User user){
		Challenge findChallenge = isExistChallenge(id);

		Challenge modifiedChallenge = challengeRepository.save(findChallenge);
		return ResponseChallengeDTO.convertDTO(modifiedChallenge);
	}


	private Challenge isExistChallenge(Long id){
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		return findChallenge.get();
	}
}

