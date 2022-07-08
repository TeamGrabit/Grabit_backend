package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.CommitApproval;
import grabit.grabit_backend.repository.CommitApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommitApprovalService {

	private final CommitApprovalRepository commitApprovalRepository;

	public CommitApprovalService(CommitApprovalRepository commitApprovalRepository) {
		this.commitApprovalRepository = commitApprovalRepository;
	}

}
