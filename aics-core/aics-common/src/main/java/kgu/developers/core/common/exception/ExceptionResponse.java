package kgu.developers.core.common.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

public record ExceptionResponse(@JsonIgnore HttpStatus status, String code, String message) {

	@Builder
	public ExceptionResponse {
	}

	public static ExceptionResponse from(CustomException exception) {
		return ExceptionResponse.builder()
			.status(exception.getCode().getStatus())
			.code(exception.getCode().getCode())
			.message(exception.getCode().getMessage())
			.build();
	}

	public static ExceptionResponse from(ExceptionCode code) {
		return ExceptionResponse.builder()
			.status(code.getStatus())
			.code(code.getCode())
			.message(code.getMessage())
			.build();
	}

	public static ExceptionResponse of(HttpStatus status, String code, String message) {
		return ExceptionResponse.builder()
			.status(status)
			.code(code)
			.message(message)
			.build();
	}
}
