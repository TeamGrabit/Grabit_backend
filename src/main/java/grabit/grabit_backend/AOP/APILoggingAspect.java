package grabit.grabit_backend.AOP;

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

	private static final Logger logger = LoggerFactory.getLogger(APILoggingAspect.class);

	@Pointcut("execution(* grabit.grabit_backend.Controller.*Controller.*(..))")
	public void controllerLog(){}

	@Around(value = "controllerLog()")
	public Object aroundControllerLog(ProceedingJoinPoint pjp) throws Throwable{
		LocalDateTime currentTime = LocalDateTime.now();
		String currentMethod = pjp.getSignature().toString();
		logger.info("## Current LocalDataTime ## : " + currentTime);
		logger.info("## Call API ## : " + currentMethod);

		// API 시간 측정 시작
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// API 수행
		ResponseEntity obj = (ResponseEntity)pjp.proceed();

		// API 시간 측정 끝
		stopWatch.stop();
		logger.info("## API 수행 시간 ## : " + stopWatch.getTotalTimeMillis() + " (ms)초");

		return obj;
	}
}
