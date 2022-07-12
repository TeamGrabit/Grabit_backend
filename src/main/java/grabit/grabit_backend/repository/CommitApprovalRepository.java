package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.CommitApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitApprovalRepository extends JpaRepository<CommitApproval, Long> {

}
