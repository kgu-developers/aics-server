package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileExceptionCode implements ExceptionCode {
	FILE_IS_NULL_EXCEPTION(HttpStatus.BAD_REQUEST, "업로드 하려는 파일이 존재하지 않습니다.")
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
