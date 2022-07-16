package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.PassApproval;
import grabit.grabit_backend.domain.PassApprovalList;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.CreatePassApprovalDTO;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.exception.NotFoundChallengeException;
import grabit.grabit_backend.exception.NotFoundPassApprovalException;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.PassApprovalListRepository;
import grabit.grabit_backend.repository.PassApprovalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassApprovalService {

	private final PassApprovalRepository passApprovalRepository;
	private final PassApprovalListRepository passApprovalListRepository;
	private final ChallengeRepository challengeRepository;

	public PassApprovalService(PassApprovalRepository passApprovalRepository, ChallengeRepository challengeRepository, PassApprovalListRepository passApprovalListRepository) {
		this.passApprovalRepository = passApprovalRepository;
		this.passApprovalListRepository = passApprovalListRepository;
		this.challengeRepository = challengeRepository;
	}

	@Transactional
	public PassApproval createPassApproval(CreatePassApprovalDTO createPassApprovalDTO, User user) throws NotFoundChallengeException {
		PassApproval passApproval = PassApproval.builder()
				.targetDate(createPassApprovalDTO.getTargetDate())
				.content(createPassApprovalDTO.getContent())
				.user(user).build();

		List<PassApprovalList> passApprovalLists = new ArrayList<>();
		Challenge challenge = challengeRepository.findChallengeById(createPassApprovalDTO.getChallengeId())
						.orElseThrow(() -> new NotFoundChallengeException());

		challenge.getUserChallengeList().forEach(
				x -> passApprovalLists.add(PassApprovalList.builder()
						.passApproval(passApproval)
						.challenge(challenge)
						.user(x.getUser())
						.status("미승인")
						.build())
		);

		passApprovalRepository.save(passApproval);
		passApprovalListRepository.saveAll(passApprovalLists);

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
