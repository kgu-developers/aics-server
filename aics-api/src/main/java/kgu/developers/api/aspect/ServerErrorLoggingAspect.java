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
		Throwable cause = exception.getCause();
		StackTraceElement[] stackTrace = exception.getStackTrace();

		log.error("[SERVER ERROR] CAUSE : {}",
		    cause != null ? cause.toString() : "No cause available"
		);
		log.error("[SERVER ERROR] FINAL POINT : {}",
		    (stackTrace != null && stackTrace.length > 0) ? stackTrace[0] : "No stack trace available"
		);
	}
}
