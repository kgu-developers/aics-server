package kgu.developers.apis.api.user.presentation.exception;

import kgu.developers.core.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Getter
@AllArgsConstructor
public enum UserExceptionCode implements ExceptionCode {
	USER_ID_DUPLICATED(CONFLICT, "이미 동일한 학번으로 가입이 되어있습니다."),
	USER_EMAIL_ID_DUPLICATED(CONFLICT, "이미 동일한 이메일로 가입이 되어있습니다."),
	USER_PHONE_NUMBER_DUPLICATED(CONFLICT, "이미 동일한 전화번호로 가입이 되어있습니다."),
	USER_NOT_AUTHENTICATED(FORBIDDEN, "회원 인증에 실패하였습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
