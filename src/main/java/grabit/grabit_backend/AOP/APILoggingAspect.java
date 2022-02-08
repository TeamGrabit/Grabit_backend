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
		logger.info("## Current LocalDataTime ## : " + currentTime);
		logger.info("## Call API ## : " + currentMethod);

		// request Log 저장.
		RequestLog requestLog = new RequestLog();
		requestLog.setCalledAt(currentTime);
		requestLog.setData(currentMethod);
		requestLog.setType("API");
		requestLogRepository.save(requestLog);

		// API 시간 측정 시작
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// API 수행
		ResponseEntity obj = (ResponseEntity)pjp.proceed();

		// API 시간 측정 끝
		stopWatch.stop();
		logger.info("## API 수행 시간 ## : " + stopWatch.getTotalTimeMillis() + " (ms)초");

		// response Log 저장.
		ResponseLog responseLog = new ResponseLog();
		responseLog.setResponseAt(LocalDateTime.now());
		responseLog.setRequestLog(requestLog);
		responseLog.setResponseBody(obj.getBody().toString());
		responseLog.setProcessTime(stopWatch.getTotalTimeMillis());
		responseLog.setHttpStatus(obj.getStatusCode().toString());
		responseLogRepository.save(responseLog);

		return obj;
	}
}
