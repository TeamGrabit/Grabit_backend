package grabit.grabit_backend.AOP;

import grabit.grabit_backend.Domain.GrabitLog;
import grabit.grabit_backend.Repository.GrabitLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class RequestLoggingAspect {

	private GrabitLogRepository grabitLogRepository;

	public RequestLoggingAspect(GrabitLogRepository grabitLogRepository){
		this.grabitLogRepository = grabitLogRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

	@Pointcut("execution(* grabit.grabit_backend.Controller.*Controller.*(..))")
	public void saveLog(){}

	@Before(value = "saveLog()")
	public void logBefore(JoinPoint pjp) {
		LocalDateTime currentTime = LocalDateTime.now();
		String currentMethod = pjp.getSignature().toString();
		logger.info("## Current LocalDataTime ## : " + currentTime);
		logger.info("## Call API ## : " + currentMethod);

		GrabitLog grabitLog = new GrabitLog();
		grabitLog.setCalledAt(currentTime);
		grabitLog.setData(currentMethod);
		grabitLog.setType("API");
		grabitLogRepository.save(grabitLog);
	}
}
