package kgu.developers.common.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public class CustomException extends RuntimeException {
	private final ExceptionCode code;

	public CustomException(ExceptionCode code) {
		super(code.getMessage());
		this.code = code;
	}

	public CustomException(ExceptionCode code, Throwable cause) {
		super(code.getMessage(), cause);
		this.code = code;
	}

	public boolean isServerError() {
		return code.getStatus().equals(INTERNAL_SERVER_ERROR);
	}
}