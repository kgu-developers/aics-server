package kgu.developers.common.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AdminExceptionCode implements ExceptionCode {
	NOT_ADMIN(FORBIDDEN, "관리자 전용 API입니다."),
	;

	private final HttpStatus status;
	private final String message;


	@Override
	public String getCode() {
		return this.name();
	}
}
