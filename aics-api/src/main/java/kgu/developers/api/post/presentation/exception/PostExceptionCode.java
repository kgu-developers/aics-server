package kgu.developers.api.post.presentation.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum PostExceptionCode implements ExceptionCode {
	POST_NOT_FOUND(NOT_FOUND, "해당 ID의 게시글이 없습니다.");

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
