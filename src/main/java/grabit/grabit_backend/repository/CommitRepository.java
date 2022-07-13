package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
}
