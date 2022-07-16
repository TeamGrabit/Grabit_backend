package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.PassApprovalList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassApprovalListRepository extends JpaRepository<PassApprovalList, Long> {
}
