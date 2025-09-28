package kgu.developers.domain.file.exception;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum FileDomainExceptionCode implements ExceptionCode {
	FILE_DIRECTORY_CREATION_FAILED(BAD_REQUEST, "파일 디렉토리 생성에 실패하였습니다."),
	FILE_STORE_FAILED(BAD_REQUEST, "파일 저장에 실패하였습니다."),
	FILE_PATH_INVALID(BAD_REQUEST, "파일 경로가 올바르지 않습니다."),
	NOT_SUPPORTED_FILE_EXTENSION(BAD_REQUEST, "지원하지 않는 파일 확장자입니다."),
	FILE_NOT_FOUND(NOT_FOUND, "파일을 찾을 수 없습니다."),
	FILE_ID_REQUIRED(BAD_REQUEST, "파일 ID는 필수입니다.")
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
