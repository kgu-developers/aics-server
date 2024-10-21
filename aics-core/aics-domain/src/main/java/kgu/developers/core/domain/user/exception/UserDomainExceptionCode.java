package kgu.developers.core.domain.user.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import kgu.developers.core.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserDomainExceptionCode implements ExceptionCode {
	USER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
	;
	
	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
