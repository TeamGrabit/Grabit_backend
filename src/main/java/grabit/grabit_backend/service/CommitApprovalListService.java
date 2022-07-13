package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Commit;
import grabit.grabit_backend.domain.CommitApproval;
import grabit.grabit_backend.domain.CommitApprovalList;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.repository.CommitApprovalListRepository;
import grabit.grabit_backend.repository.CommitApprovalRepository;
import grabit.grabit_backend.repository.CommitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommitApprovalListService {

	private final CommitApprovalListRepository commitApprovalListRepository;
	private final CommitApprovalRepository commitApprovalRepository;
	private final CommitRepository commitRepository;
	private static final String COMMIT_ACCEPT = "승인";
	private static final String COMMIT_REJECT = "거절";

	public CommitApprovalListService(CommitApprovalListRepository commitApprovalListRepository, CommitApprovalRepository commitApprovalRepository, CommitRepository commitRepository) {
		this.commitApprovalListRepository = commitApprovalListRepository;
		this.commitApprovalRepository = commitApprovalRepository;
		this.commitRepository = commitRepository;
	}

	@Transactional
	public String acceptCommitApproval(Long id, User user) {
		CommitApprovalList commitApprovalList = readCommitApproval(id, user);
		Integer allCount = commitApprovalListRepository.countByCommitApproval(commitApprovalList.getCommitApproval());
		Integer acceptCount = commitApprovalListRepository.countByCommitApprovalAndStatus(commitApprovalList.getCommitApproval(), COMMIT_ACCEPT);

		if ((acceptCount+1)/(double)allCount >= 0.5) {
			CommitApproval commitApproval = commitApprovalList.getCommitApproval();
			Commit commit = Commit.builder()
					.user(commitApproval.getUser())
					.challenge(commitApprovalList.getChallenge())
					.date(commitApproval.getTargetDate()).build();
			commitRepository.save(commit);
			commitApprovalRepository.delete(commitApproval);
		}else{
			commitApprovalList.setStatus(COMMIT_ACCEPT);
			commitApprovalListRepository.save(commitApprovalList);
		}

		return COMMIT_ACCEPT;
	}

	@Transactional
	public String rejectCommitApproval(Long id, User user) {
		CommitApprovalList commitApprovalList = readCommitApproval(id, user);
		Integer allCount = commitApprovalListRepository.countByCommitApproval(commitApprovalList.getCommitApproval());
		Integer rejectCount = commitApprovalListRepository.countByCommitApprovalAndStatus(commitApprovalList.getCommitApproval(), COMMIT_REJECT);

		if ((rejectCount+1)/(double)allCount >= 0.5) {
			CommitApproval commitApproval = commitApprovalList.getCommitApproval();
			commitApprovalRepository.delete(commitApproval);
		}else{
			commitApprovalList.setStatus(COMMIT_REJECT);
			commitApprovalListRepository.save(commitApprovalList);
		}

		return COMMIT_REJECT;
	}

	public CommitApprovalList readCommitApproval(Long id, User user) {
		CommitApprovalList commitApprovalList = commitApprovalListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("승인 요청 정보를 찾을 수 없습니다."));

		if (!commitApprovalList.getUser().getId().equals(user.getId())) {
			throw new ForbiddenException();
		}

		return commitApprovalList;
	}
}
