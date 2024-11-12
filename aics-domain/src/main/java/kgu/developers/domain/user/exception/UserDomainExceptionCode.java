package kgu.developers.domain.user.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserDomainExceptionCode implements ExceptionCode {
	USER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
	EMAIL_NOT_VALID(BAD_REQUEST, "학교 이메일 형식이 아닙니다."),
	DEPARTMENT_CODE_NOT_VALID(BAD_REQUEST, "학번별 학과 코드가 일치하지 않습니다.")
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
