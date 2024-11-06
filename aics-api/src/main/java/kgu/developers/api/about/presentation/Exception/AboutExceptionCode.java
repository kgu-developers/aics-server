package kgu.developers.api.about.presentation.Exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AboutExceptionCode implements ExceptionCode {
	CATEGORY_NOT_MATCH(BAD_REQUEST, "메인 카테고리와 보조 카테고리가 맞지 않습니다.");

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
