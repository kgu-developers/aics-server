package kgu.developers.admin.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import kgu.developers.common.exception.CustomException;
import kgu.developers.globalutils.logging.LoggingUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Profile("local")
public class ExceptionLoggingAspect {

	@Pointcut("execution(* kgu.developers.admin..application..*(..)) && "
		+ "!execution(* kgu.developers.common..*(..)) && "
		+ "!@annotation(kgu.developers.globalutils.annotation.NoLogging) && "
		+ "!@annotation(org.springframework.boot.context.properties.ConfigurationProperties)"
	)
	private void logPointcut() {
	}

	@AfterThrowing(value = "logPointcut()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, CustomException exception) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();

		List<String> arguments = LoggingUtils.getArguments(joinPoint);
		String parameterMessage = LoggingUtils.getParameterMessage(arguments);

		log.error("[ERROR] POINT : {} || EXCEPTION : {} || ARGUMENTS : {}", className, exception.getCode().getCode(),
			parameterMessage);
		log.error("[ERROR] FINAL POINT : {}", exception.getStackTrace()[0]);
		log.error("[ERROR] MESSAGE : {}", exception.getMessage());
	}
}
