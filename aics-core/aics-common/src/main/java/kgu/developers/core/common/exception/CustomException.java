package kgu.developers.core.common.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final ExceptionCode code;

	public CustomException(ExceptionCode code) {
		super(code.getMessage());
		this.code = code;
	}

	public boolean isServerError() {
		return code.getStatus().equals(INTERNAL_SERVER_ERROR);
	}
}