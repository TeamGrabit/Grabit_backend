package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.PassApproval;
import grabit.grabit_backend.domain.PassApprovalResult;
import grabit.grabit_backend.enums.PassApprovalResultStatus;
import grabit.grabit_backend.domain.Pass;
import grabit.grabit_backend.domain.PassApprovalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassApprovalResultRepository extends JpaRepository<PassApprovalResult, Long> {
	Integer countByPassApproval(PassApproval passApproval);
	Integer countByPassApprovalAndStatus(PassApproval passApproval, PassApprovalResultStatus status);
}
