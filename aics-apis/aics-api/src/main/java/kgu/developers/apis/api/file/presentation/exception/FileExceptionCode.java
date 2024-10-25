package kgu.developers.apis.api.file.presentation.exception;

import kgu.developers.core.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum FileExceptionCode implements ExceptionCode {
	FILE_IS_NULL_EXCEPTION(BAD_REQUEST, "업로드 하려는 파일이 존재하지 않습니다."),
	EXTENSION_IS_NOT_VALID(NOT_ACCEPTABLE, "파일이 유효한 형식이 아닙니다."),
	FILE_IS_TOO_BIG(PAYLOAD_TOO_LARGE, "파일의 크기가 너무 큽니다."),
	FILE_PATH_IS_NOT_VALID(INTERNAL_SERVER_ERROR, "저장된 파일 위치가 확인되지 않습니다."),
	FILE_SAVING_EXCEPTION(INTERNAL_SERVER_ERROR, "파일 저장이 되지 않았습니다.")
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
