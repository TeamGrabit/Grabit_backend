package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.PassApprovalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassApprovalResultRepository extends JpaRepository<PassApprovalResult, Long> {
}
