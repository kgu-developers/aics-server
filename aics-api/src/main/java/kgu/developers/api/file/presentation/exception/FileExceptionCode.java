package kgu.developers.api.file.presentation.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum FileExceptionCode implements ExceptionCode {
	FILE_SAVING_EXCEPTION(INTERNAL_SERVER_ERROR, "파일 저장이 되지 않았습니다.")
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
