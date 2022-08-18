package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.PassApproval;
import grabit.grabit_backend.domain.PassApprovalResult;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.CreatePassApprovalDTO;
import grabit.grabit_backend.enums.PassApprovalResultStatus;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.exception.NotFoundChallengeException;
import grabit.grabit_backend.exception.NotFoundPassApprovalException;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.PassApprovalResultRepository;
import grabit.grabit_backend.repository.PassApprovalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassApprovalService {

	private final PassApprovalRepository passApprovalRepository;
	private final PassApprovalResultRepository passApprovalResultRepository;
	private final ChallengeRepository challengeRepository;

	public PassApprovalService(PassApprovalRepository passApprovalRepository, ChallengeRepository challengeRepository, PassApprovalResultRepository passApprovalResultRepository) {
		this.passApprovalRepository = passApprovalRepository;
		this.passApprovalResultRepository = passApprovalResultRepository;
		this.challengeRepository = challengeRepository;
	}

	@Transactional
	public PassApproval createPassApproval(CreatePassApprovalDTO createPassApprovalDTO, User user) throws NotFoundChallengeException {
		PassApproval passApproval = PassApproval.builder()
				.targetDate(createPassApprovalDTO.getTargetDate())
				.content(createPassApprovalDTO.getContent())
				.user(user).build();

		List<PassApprovalResult> passApprovalResults = new ArrayList<>();
		Challenge challenge = challengeRepository.findChallengeById(createPassApprovalDTO.getChallengeId())
						.orElseThrow(() -> new NotFoundChallengeException());

		challenge.getUserChallengeList().forEach(
				x -> passApprovalResults.add(PassApprovalResult.builder()
						.passApproval(passApproval)
						.challenge(challenge)
						.user(x.getUser())
						.status(PassApprovalResultStatus.PENDING)
						.build())
		);

		passApprovalRepository.save(passApproval);
		passApprovalResultRepository.saveAll(passApprovalResults);

		return passApproval;
	}

	public PassApproval readPassApproval(Long id) {
		PassApproval passApproval = passApprovalRepository.findById(id)
				.orElseThrow(() -> new NotFoundPassApprovalException());

		return passApproval;
	}

	@Transactional
	public void deletePassApproval(Long id, User user) {
		PassApproval passApproval = passApprovalRepository.findById(id)
				.orElseThrow(() -> new NotFoundPassApprovalException());

		if (!passApproval.getUser().getId().equals(user.getId())) {
			throw new ForbiddenException();
		}

		passApprovalRepository.delete(passApproval);
	}

}
