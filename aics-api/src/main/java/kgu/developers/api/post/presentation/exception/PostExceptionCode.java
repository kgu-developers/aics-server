package kgu.developers.api.post.presentation.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostExceptionCode implements ExceptionCode {
	UNAUTHORIZED_AUTHOR(FORBIDDEN, "작성자만 수정 또는 삭제할 수 있습니다."),
	POST_NOT_FOUND(NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
