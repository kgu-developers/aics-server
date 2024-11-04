package kgu.developers.api.professor.presentation.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProfessorExceptionCode implements ExceptionCode {
	PROFESSOR_NOT_FOUND(NOT_FOUND, "해당 ID의 교수가 없습니다.");

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
