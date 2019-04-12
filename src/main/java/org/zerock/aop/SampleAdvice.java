package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	private static final Logger log = LoggerFactory.getLogger(SampleAdvice.class);
	
//	@Before("execution(* org.zerock.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("--------------------------------");
		log.info("--------------------------------");
		log.info(Arrays.toString(jp.getArgs()));
	}
	
//	@Around("execution(* org.zerock.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		
		log.info("================================");
		log.info("{} : {}", pjp.getSignature().getName(), endTime - startTime);
		log.info("================================");
		
		return result;
	}
}
