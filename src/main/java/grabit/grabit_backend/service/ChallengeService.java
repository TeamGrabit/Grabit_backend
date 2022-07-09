package grabit.grabit_backend.service;

import com.amazonaws.services.kms.model.NotFoundException;
import grabit.grabit_backend.domain.JoinChallengeRequest;
import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.dto.ModifyChallengeDTO;
import grabit.grabit_backend.dto.ResponseChallengeDTO;
import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.exception.UnauthorizedException;
import grabit.grabit_backend.domain.UserChallenge;
import grabit.grabit_backend.repository.JoinChallengeRequestRepository;
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
	private final JoinChallengeRequestRepository joinChallengeRequestRepository;

	@Autowired
	public ChallengeService(ChallengeRepository challengeRepository,
							UserChallengeRepository userChallengeRepository,
							UserRepository userRepository,
							JoinChallengeRequestRepository joinChallengeRequestRepository){
		this.challengeRepository = challengeRepository;
		this.userChallengeRepository = userChallengeRepository;
		this.userRepository = userRepository;
		this.joinChallengeRequestRepository = joinChallengeRequestRepository;
	}

	/**
	 * 챌린지 생성
	 * @param createChallengeDTO
	 * @return id
	 */
	@Transactional
	public Challenge createChallenge(CreateChallengeDTO createChallengeDTO, User user){
		Challenge challenge = Challenge.createChallenge(createChallengeDTO, user);
		Challenge createChallenge = challengeRepository.save(challenge);

		UserChallenge userChallenge = UserChallenge.createUserChallenge(challenge, user);
		userChallengeRepository.save(userChallenge);

		List<UserChallenge> userChallengeList = new ArrayList<>();
		userChallengeList.add(userChallenge);
		createChallenge.setUserChallengeList(userChallengeList);

		return createChallenge;
	}

	/**
	 * 챌린지 조회 (id)
	 * @param id
	 * @return Challenge
	 */
	@Transactional
	public Challenge findChallengeById(Long id){
		Optional<Challenge> findChallenge = challengeRepository.findChallengeById(id);
		if(findChallenge.isEmpty()){
			throw new IllegalStateException("존재하지 않는 챌린지입니다..");
		}
		return findChallenge.get();
	}

	/**
	 * 챌린지 조회 (name)
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
		Challenge findChallenge = findChallengeById(id);

		// leader 여부 확인.
		if(!findChallenge.getLeader().getUserId().equals(user.getUserId())){
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
	public Challenge updateChallenge(Long id, ModifyChallengeDTO modifyChallengeDTO, User user){
		Challenge findChallenge = findChallengeById(id);

		// leader 여부 확인.
		if(!findChallenge.getLeader().getId().equals(user.getId())){
			throw new UnauthorizedException();
		}

		Optional<User> leader = userRepository.findByUserId(modifyChallengeDTO.getLeader());
		if(leader.isEmpty()){
			throw new IllegalStateException("존재하지 않는 유저입니다.");
		}

		User findLeader = leader.get();
		findChallenge.modifyChallenge(modifyChallengeDTO, findLeader);
		Challenge modifiedChallenge = challengeRepository.save(findChallenge);

		return modifiedChallenge;
	}

	/**
	 * 챌린지 조회 with Paging
	 * @param page
	 * @param size
	 * @return
	 */
	@Transactional
	public Page<Challenge> findAllChallengeWithPage(Integer page, Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return challengeRepository.findAllChallengeWithPaging(pageRequest);
	}

	/**
	 * 챌린지 가입
	 * @param id
	 * @param user
	 * @return
	 */
	@Transactional
	public Challenge joinChallenge(Long id, User user){
		Challenge challenge = findChallengeById(id);

		Optional<UserChallenge> findUserChallenge = userChallengeRepository.findByUserAndChallenge(user, challenge);
		if(findUserChallenge.isPresent()){
			throw new IllegalStateException("이미 가입한 유저입니다.");
		}

		if (challenge.getIsPrivate()) {
			JoinChallengeRequest joinChallengeRequest = JoinChallengeRequest.builder()
					.user(user)
					.challenge(challenge)
					.build();
			this.joinChallengeRequestRepository.save(joinChallengeRequest);
			return null;
		}

		UserChallenge userChallenge = UserChallenge.builder()
				.user(user)
				.challenge(challenge)
				.build();

		userChallengeRepository.save(userChallenge);
		challenge.getUserChallengeList().add(userChallenge);

		return challenge;
	}

	/**
	 * 챌린지 가입 승인
	 * @param user
	 * @param joinChallengeRequestId
	 */
	public Challenge approveJoinChallengeRequest(User user, Long joinChallengeRequestId) {
		JoinChallengeRequest request = getJoinChallengeRequestUser(joinChallengeRequestId);
		Challenge challenge = request.getChallenge();

		checkIsLeader(user, challenge);

		request.setStatus(1);
		this.joinChallengeRequestRepository.save(request);

		UserChallenge userChallenge = UserChallenge.builder()
				.user(user)
				.challenge(challenge)
				.build();

		userChallengeRepository.save(userChallenge);
		challenge.getUserChallengeList().add(userChallenge);

		return challenge;
	}

	/**
	 * 챌린지 가입 거절
	 * @param user
	 * @param joinChallengeRequestId
	 */
	public void rejectJoinChallengeRequest(User user, Long joinChallengeRequestId) {
		JoinChallengeRequest request = getJoinChallengeRequestUser(joinChallengeRequestId);
		Challenge challenge = request.getChallenge();

		checkIsLeader(user, challenge);

		request.setStatus(2);
		this.joinChallengeRequestRepository.save(request);
	}

	/**
	 * 챌린지 탈퇴
	 * @param id
	 * @param user
	 * @return
	 */
	@Transactional
	public void leaveChallenge(Long id, User user){
		Challenge findChallenge = findChallengeById(id);
		userChallengeRepository.deleteByUserAndChallenge(user, findChallenge);
	}

	/**
	 * 챌린지 가입 요청 조회 by ID
	 */
	private JoinChallengeRequest getJoinChallengeRequestUser(Long joinChallengeRequestId) {
		Optional<JoinChallengeRequest> request = this.joinChallengeRequestRepository.findById(joinChallengeRequestId);
		if (request.isEmpty()) {
			throw new NotFoundException("존재하지 않는 요청입니다.");
		}
		return request.get();
	}

	/**
	 * 챌린지 가입 요청중인 유저 목록
	 */
	public List<JoinChallengeRequest> getJoinChallengeRequestUserList(User user, Long challengeId) {
		Challenge challenge = findChallengeById(challengeId);

		checkIsLeader(user, challenge);

		List<JoinChallengeRequest> requestList = this.joinChallengeRequestRepository.findByChallenge(challenge);
		return requestList;
	}

	private void checkIsLeader(User user, Challenge challenge) {
		if (!challenge.getLeader().equals(user)) {
			throw new ForbiddenException("권한이 없습니다.");
		}
	}

	public Page<Challenge> findUserJoinedChallengeList(User user, Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return challengeRepository.findUserJoinedChallengeList(pageRequest, user);
	}
}

