package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.CommitApproval;
import grabit.grabit_backend.domain.CommitApprovalList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitApprovalListRepository extends JpaRepository<CommitApprovalList, Long> {
	Integer countByCommitApproval(CommitApproval commitApproval);
	Integer countByCommitApprovalAndStatus(CommitApproval commitApproval, String status);
}
