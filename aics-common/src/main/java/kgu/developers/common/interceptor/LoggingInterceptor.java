package kgu.developers.common.interceptor;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import kgu.developers.globalutils.annotation.NoLogging;
import kgu.developers.globalutils.logging.LoggingUtils;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response,
		@NotNull Object handler) {
		if (isNoLogging(handler)) return true;
		request.setAttribute("startTime", System.currentTimeMillis());
		LoggingUtils.logRequest();
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) {
		if (isNoLogging(handler)) return;
		LoggingUtils.logDuration(request, response, ex);
	}

	private boolean isNoLogging(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			Method method = handlerMethod.getMethod();

			return method.isAnnotationPresent(NoLogging.class);
		}
		return false;
	}
}
