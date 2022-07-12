package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.CommitApprovalList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitApprovalListRepository extends JpaRepository<CommitApprovalList, Long> {
}
