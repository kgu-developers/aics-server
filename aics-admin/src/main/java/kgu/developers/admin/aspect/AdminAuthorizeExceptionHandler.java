package kgu.developers.admin.aspect;

import static kgu.developers.admin.exception.AdminExceptionCode.NOT_ADMIN;

import kgu.developers.common.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AdminAuthorizeExceptionHandler {
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionResponse> handleAccessDeniedException() {
		ExceptionResponse response = ExceptionResponse.from(NOT_ADMIN);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
}
