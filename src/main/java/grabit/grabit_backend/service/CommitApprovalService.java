package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.CommitApproval;
import grabit.grabit_backend.domain.CommitApprovalList;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.CreateCommitApprovalDTO;
import grabit.grabit_backend.exception.NotFoundChallengeException;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.CommitApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommitApprovalService {

	private final CommitApprovalRepository commitApprovalRepository;
	private final ChallengeRepository challengeRepository;

	public CommitApprovalService(CommitApprovalRepository commitApprovalRepository, ChallengeRepository challengeRepository) {
		this.commitApprovalRepository = commitApprovalRepository;
		this.challengeRepository = challengeRepository;
	}

	public CommitApproval createCommitApproval(CreateCommitApprovalDTO createCommitApprovalDTO, User user) throws NotFoundChallengeException {
		CommitApproval commitApproval = CommitApproval.builder()
				.targetDate(createCommitApprovalDTO.getTargetDate())
				.content(createCommitApprovalDTO.getContent()).build();

		List<CommitApprovalList> commitApprovalLists = new ArrayList<>();
		Challenge challenge = challengeRepository.findChallengeById(createCommitApprovalDTO.getChallengeId())
						.orElseThrow(() -> new NotFoundChallengeException());

		challenge.getUserChallengeList().forEach(
				x -> commitApprovalLists.add(CommitApprovalList.builder()
						.commitApproval(commitApproval)
						.challenge(challenge)
						.user(user)
						.status("미승인")
						.build())
		);
		commitApproval.setCommitApprovalList(commitApprovalLists);
		commitApprovalRepository.save(commitApproval);

		return commitApproval;
	}

}
