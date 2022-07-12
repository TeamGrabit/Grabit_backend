package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.CommitApprovalList;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.repository.CommitApprovalListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommitApprovalListService {

	private final CommitApprovalListRepository commitApprovalListRepository;
	private static final String COMMIT_ACCEPT = "승인";
	private static final String COMMIT_REJECT = "거절";

	public CommitApprovalListService(CommitApprovalListRepository commitApprovalListRepository) {
		this.commitApprovalListRepository = commitApprovalListRepository;
	}

	@Transactional
	public String acceptCommitApproval(Long id, User user) {
		CommitApprovalList commitApprovalList = readCommitApproval(id, user);

		commitApprovalList.setStatus(COMMIT_ACCEPT);
		commitApprovalListRepository.save(commitApprovalList);
		return COMMIT_ACCEPT;
	}

	@Transactional
	public String rejectCommitApproval(Long id, User user) {
		CommitApprovalList commitApprovalList = readCommitApproval(id, user);

		commitApprovalList.setStatus(COMMIT_REJECT);
		commitApprovalListRepository.save(commitApprovalList);
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
