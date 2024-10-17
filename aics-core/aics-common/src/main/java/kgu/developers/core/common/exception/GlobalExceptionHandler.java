package kgu.developers.core.common.exception;


import static kgu.developers.core.common.exception.GlobalExceptionCode.INVALID_INPUT;
import static kgu.developers.core.common.exception.GlobalExceptionCode.SERVER_ERROR;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private final ApplicationEventPublisher eventPublisher;

	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException exception) {
		if (exception.isServerError())
			eventPublisher.publishEvent(exception);
		ExceptionResponse response = ExceptionResponse.from(exception);
		return ResponseEntity.status(response.status()).body(response);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ExceptionResponse> handleException(Exception exception) {
		eventPublisher.publishEvent(exception);
		return ResponseEntity.internalServerError().body(ExceptionResponse.from(SERVER_ERROR));
	}

	@Override
	protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException exception,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String message = exception.getAllValidationResults().stream()
			.map(ParameterValidationResult::getResolvableErrors)
			.flatMap(List::stream)
			.map(MessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.joining(", "));
		ExceptionResponse response = ExceptionResponse.of(INVALID_INPUT.getStatus(), INVALID_INPUT.getCode(), message);

		return ResponseEntity.status(response.status()).body(response);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String message = exception.getFieldErrors().stream()
			.map(error -> error.getField() + ": " + error.getDefaultMessage())
			.collect(Collectors.joining(", "));
		ExceptionResponse response = ExceptionResponse.of(INVALID_INPUT.getStatus(), INVALID_INPUT.getCode(), message);

		return ResponseEntity.status(response.status()).body(response);
	}
}