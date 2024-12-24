package kgu.developers.api.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import kgu.developers.common.exception.CustomException;
import kgu.developers.globalutils.logging.LoggingUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ServerErrorLoggingAspect {

	@Pointcut("execution(public * kgu.developers..*(..)) && "
		+ "!execution(* kgu.developers.api..presentation..*(..)) && "
		+ "!@annotation(org.springframework.boot.context.properties.ConfigurationProperties)"
	)
	private void logPointcut() {
	}

	@AfterThrowing(value = "logPointcut()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
		if (exception instanceof CustomException) return;
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();

		List<String> arguments = LoggingUtils.getArguments(joinPoint);
		String parameterMessage = LoggingUtils.getParameterMessage(arguments);

		log.error("[SERVER ERROR] POINT : {} || ARGUMENTS : {}", className, parameterMessage);
		log.error("[SERVER ERROR] MESSAGE : {}", exception.getMessage());
		log.error("[SERVER ERROR] CAUSE : {}", exception.getCause().toString());
		log.error("[SERVER ERROR] FINAL POINT : {}", exception.getStackTrace()[0]);
	}
}
