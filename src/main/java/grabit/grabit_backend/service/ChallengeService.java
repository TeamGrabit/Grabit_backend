package grabit.grabit_backend.service;

import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.dto.ModifyChallengeDTO;
import grabit.grabit_backend.dto.ResponseChallengeDTO;
import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.exception.UnauthorizedException;
import grabit.grabit_backend.domain.UserChallenge;
import grabit.grabit_backend.repository.UserChallengeRepository;
import grabit.grabit_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

	private final ChallengeRepository challengeRepository;
	private final UserChallengeRepository userChallengeRepository;
	private final UserRepository userRepository;

	@Autowired
	public ChallengeService(ChallengeRepository challengeRepository,
							UserChallengeRepository userChallengeRepository,
							UserRepository userRepository){
		this.challengeRepository = challengeRepository;
		this.userChallengeRepository = userChallengeRepository;
		this.userRepository = userRepository;
	}

	/**
	 * 챌린지 생성
	 * @param createChallengeDTO
	 * @return id
	 */
	@Transactional
	public ResponseChallengeDTO createChallenge(CreateChallengeDTO createChallengeDTO, User user){
		Challenge challenge = new Challenge(createChallengeDTO.getName(),
				createChallengeDTO.getDescription(),
				createChallengeDTO.getIsPrivate(),
				user
		);
		Challenge createChallenge = challengeRepository.save(challenge);

		UserChallenge userChallenge = new UserChallenge();
		userChallenge.setChallenge(createChallenge);
		userChallenge.setUser(user);
		userChallengeRepository.save(userChallenge);

		return ResponseChallengeDTO.convertDTO(createChallenge);
	}

	/**
	 * 챌린지 검색 (id)
	 * @param id
	 * @return Challenge
	 */
	@Transactional
	public ResponseChallengeDTO findChallengeById(Long id){
		Challenge challenge = isExistChallenge(id);
		return ResponseChallengeDTO.convertDTO(challenge);
	}

	/**
	 * 챌린지 검색 (name)
	 * @param name
	 * @return List of Challenge
	 */
	@Transactional
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
	@Transactional
	public void deleteChallengeById(Long id, User user){
		Challenge findChallenge = isExistChallenge(id);

		// leader 여부 확인.
		if(findChallenge.getLeader().getUserId() != user.getUserId()){
			throw new UnauthorizedException();
		}

		challengeRepository.deleteById(id);
	}

	/**
	 * 챌린지 수정
	 * @param id
	 * @param modifyChallengeDTO
	 * @return Challenge
	 */
	@Transactional
	public ResponseChallengeDTO updateChallenge(Long id, ModifyChallengeDTO modifyChallengeDTO, User user){
		Challenge findChallenge = isExistChallenge(id);

		// leader 여부 확인.
		if(findChallenge.getLeader().getId() != user.getId()){
			throw new UnauthorizedException();
		}
		Optional<User> leader = userRepository.findByUserId(modifyChallengeDTO.getLeader());
		if(leader.isEmpty()){
			throw new IllegalStateException("존재하지 않는 유저입니다.");
		}
		User findLeader = leader.get();
		findChallenge.modifyChallenge(modifyChallengeDTO, findLeader);
		Challenge modifiedChallenge = challengeRepository.save(findChallenge);
		return ResponseChallengeDTO.convertDTO(modifiedChallenge);
	}

	/**
	 * 챌린지 검색 with Paging
	 * @param page
	 * @param size
	 * @return
	 */
	@Transactional
	public Page<Challenge> findAllChallengeWithPage(Integer page, Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return challengeRepository.findAll(pageRequest);
	}

	/**
	 * 챌린지 가입
	 * @param id
	 * @param user
	 * @return
	 */
	@Transactional
	public ResponseChallengeDTO joinChallenge(Long id, User user){
		Challenge findChallenge = isExistChallenge(id);

		Optional<UserChallenge> findUserChallenge = userChallengeRepository.findByUserAndChallenge(user, findChallenge);
		if(findUserChallenge.isPresent()){
			throw new IllegalStateException("이미 가입한 유저입니다.");
		}

		UserChallenge userChallenge = UserChallenge.builder()
				.user(user)
				.challenge(findChallenge)
				.build();

		userChallengeRepository.save(userChallenge);
		findChallenge.getUserChallengeList().add(userChallenge);

		return ResponseChallengeDTO.convertDTO(findChallenge);
	}

	/**
	 * 챌린지 탈퇴
	 * @param id
	 * @param user
	 * @return
	 */
	@Transactional
	public void leaveChallenge(Long id, User user){
		Challenge findChallenge = isExistChallenge(id);

		userChallengeRepository.deleteByUserAndChallenge(user, findChallenge);
	}

	private Challenge isExistChallenge(Long id){
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		return findChallenge.get();
	}
}

