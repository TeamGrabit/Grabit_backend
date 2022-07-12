package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.CommitApproval;
import grabit.grabit_backend.domain.CommitApprovalList;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.CreateCommitApprovalDTO;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.exception.NotFoundChallengeException;
import grabit.grabit_backend.exception.NotFoundCommitApprovalException;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.CommitApprovalListRepository;
import grabit.grabit_backend.repository.CommitApprovalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommitApprovalService {

	private final CommitApprovalRepository commitApprovalRepository;
	private final CommitApprovalListRepository commitApprovalListRepository;
	private final ChallengeRepository challengeRepository;

	public CommitApprovalService(CommitApprovalRepository commitApprovalRepository, ChallengeRepository challengeRepository, CommitApprovalListRepository commitApprovalListRepository) {
		this.commitApprovalRepository = commitApprovalRepository;
		this.commitApprovalListRepository = commitApprovalListRepository;
		this.challengeRepository = challengeRepository;
	}

	@Transactional
	public CommitApproval createCommitApproval(CreateCommitApprovalDTO createCommitApprovalDTO, User user) throws NotFoundChallengeException {
		CommitApproval commitApproval = CommitApproval.builder()
				.targetDate(createCommitApprovalDTO.getTargetDate())
				.content(createCommitApprovalDTO.getContent())
				.user(user).build();

		List<CommitApprovalList> commitApprovalLists = new ArrayList<>();
		Challenge challenge = challengeRepository.findChallengeById(createCommitApprovalDTO.getChallengeId())
						.orElseThrow(() -> new NotFoundChallengeException());

		challenge.getUserChallengeList().stream()
				.filter(x -> !(x.getUser().getId().equals(user.getId())))
				.forEach(x -> commitApprovalLists.add(CommitApprovalList.builder()
						.commitApproval(commitApproval)
						.challenge(challenge)
						.user(x.getUser())
						.status("미확인")
						.build())
		);

		commitApprovalRepository.save(commitApproval);
		commitApprovalListRepository.saveAll(commitApprovalLists);

		return commitApproval;
	}

	public CommitApproval readCommitApproval(Long id) {
		CommitApproval commitApproval = commitApprovalRepository.findById(id)
				.orElseThrow(() -> new NotFoundCommitApprovalException());

		return commitApproval;
	}

	@Transactional
	public void deleteCommitApproval(Long id, User user) {
		CommitApproval commitApproval = commitApprovalRepository.findById(id)
				.orElseThrow(() -> new NotFoundCommitApprovalException());

		if (!commitApproval.getUser().getId().equals(user.getId())) {
			throw new ForbiddenException();
		}

		commitApprovalRepository.delete(commitApproval);
	}

}
