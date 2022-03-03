package grabit.grabit_backend.AOP;

import grabit.grabit_backend.Domain.RequestLog;
import grabit.grabit_backend.Domain.ResponseLog;
import grabit.grabit_backend.Repository.RequestLogRepository;
import grabit.grabit_backend.Repository.ResponseLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

@Component
@Aspect
public class APILoggingAspect {

	private RequestLogRepository requestLogRepository;
	private ResponseLogRepository responseLogRepository;

	public APILoggingAspect(RequestLogRepository requestLogRepository, ResponseLogRepository responseLogRepository){
		this.requestLogRepository = requestLogRepository;
		this.responseLogRepository = responseLogRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(APILoggingAspect.class);

	@Pointcut("execution(* grabit.grabit_backend.Controller.*Controller.*(..))")
	public void controllerLog(){}

	@Around(value = "controllerLog()")
	public Object aroundControllerLog(ProceedingJoinPoint pjp) throws Throwable{
		LocalDateTime currentTime = LocalDateTime.now();
		String currentMethod = pjp.getSignature().toString();
		logger.info("## Call API ## : " + currentMethod);

		// API 시간 측정 시작
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// API 수행
		ResponseEntity obj = (ResponseEntity)pjp.proceed();

		// API 시간 측정 끝
		stopWatch.stop();
		logger.info("## Response Data ## : " + obj);
		logger.info("## API process Time ## : " + stopWatch.getTotalTimeMillis() + " (ms)초");

		return obj;
	}
}
