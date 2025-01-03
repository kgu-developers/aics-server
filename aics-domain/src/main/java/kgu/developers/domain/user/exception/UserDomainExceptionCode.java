package kgu.developers.domain.user.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserDomainExceptionCode implements ExceptionCode {
	EMAIL_NOT_VALID(BAD_REQUEST, "학교 이메일 형식이 아닙니다."),
	DEPT_CODE_NOT_VALID(BAD_REQUEST, "학번별 학과 코드가 일치하지 않습니다."),
	INVALID_PASSWORD(BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
	USER_NOT_AUTHENTICATED(UNAUTHORIZED, "회원 인증에 실패하였습니다."),
	USER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
	USER_ID_DUPLICATED(CONFLICT, "이미 동일한 학번으로 가입이 되어있습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
