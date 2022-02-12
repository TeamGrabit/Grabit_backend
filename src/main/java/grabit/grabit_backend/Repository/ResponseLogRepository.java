package grabit.grabit_backend.Repository;

import grabit.grabit_backend.Domain.ResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseLogRepository extends JpaRepository<ResponseLog, Long> {
	ResponseLog save(ResponseLog responseLog);
}
