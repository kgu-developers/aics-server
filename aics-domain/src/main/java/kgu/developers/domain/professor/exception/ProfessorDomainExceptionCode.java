package kgu.developers.domain.professor.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import kgu.developers.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfessorDomainExceptionCode implements ExceptionCode {
	PROFESSOR_NOT_FOUND(NOT_FOUND, "해당 교수를 찾을 수 없습니다."),
	NO_SUCH_ROLE(BAD_REQUEST, "role은 \"교수\" 혹은 \"조교수\"입니다."),
	;

	private final HttpStatus status;
	private final String message;

	@Override
	public String getCode() {
		return this.name();
	}
}
