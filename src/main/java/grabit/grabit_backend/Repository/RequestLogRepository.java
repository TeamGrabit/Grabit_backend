package grabit.grabit_backend.Repository;

import grabit.grabit_backend.Domain.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
	RequestLog save(RequestLog requestLog);
}
