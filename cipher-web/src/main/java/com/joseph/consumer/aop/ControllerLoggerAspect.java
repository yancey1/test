package com.joseph.consumer.aop;

import com.alibaba.fastjson.JSONObject;
import com.joseph.common.enums.LogLevelEnum;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerLoggerAspect {

	private static final Logger logger = Logger.getLogger("request");

	@Pointcut("execution(* com.joseph.consumer.controller..*.*(..))")
	public void executeService(){

	}

	@Value("${debug.level}")
	private String logLevel = "info";

	@Before("executeService()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String paramMap = JSONObject.toJSONString(httpServletRequest.getParameterMap());
		String queryStrings = httpServletRequest.getQueryString();
		String method = httpServletRequest.getMethod();
		if (LogLevelEnum.DEBUG.getLogLevel().equals(logLevel)) {
			logger.debug(String.format(
					"request <===url:[%s], class:[%s], method:[%s], paramters:[%s],queryStrings:[%s],method:[%s]",
					httpServletRequest.getRequestURL(), className, methodName, paramMap, queryStrings, method));
		} else if (LogLevelEnum.INFO.getLogLevel().equals(logLevel)) {
			logger.info(String.format(
					"request <===url:[%s], class:[%s], method:[%s], paramters:[%s],queryStrings:[%s],method:[%s]",
					httpServletRequest.getRequestURL(), className, methodName, paramMap, queryStrings, method));
		} else if (LogLevelEnum.WARN.getLogLevel().equals(logLevel)) {
			logger.warn(String.format(
					"request <===url:[%s], class:[%s], method:[%s], paramters:[%s],queryStrings:[%s],method:[%s]",
					httpServletRequest.getRequestURL(), className, methodName, paramMap, queryStrings, method));
		} else if (LogLevelEnum.ERROR.getLogLevel().equals(logLevel)) {
			logger.error(String.format(
					"request <===url:[%s], class:[%s], method:[%s], paramters:[%s,queryStrings:[%s],method:[%s]]",
					httpServletRequest.getRequestURL(), className, methodName, paramMap, queryStrings, method));
		}
	}

	// public Object myAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
	// StopWatch stopWatch = new Log4JStopWatch(logger);
	// Object result = joinPoint.proceed();
	// MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	// Method method = signature.getMethod();
	// stopWatch.stop(method.getName());
	// return result;
	// }

}
