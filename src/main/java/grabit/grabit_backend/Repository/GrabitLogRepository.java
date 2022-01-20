package grabit.grabit_backend.Repository;

import grabit.grabit_backend.Domain.GrabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrabitLogRepository extends JpaRepository<GrabitLog, Long> {
	GrabitLog save(GrabitLog log);
}
