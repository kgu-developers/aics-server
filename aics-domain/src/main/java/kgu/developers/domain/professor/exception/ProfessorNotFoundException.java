package kgu.developers.domain.professor.exception;

import static kgu.developers.domain.professor.exception.ProfessorDomainExceptionCode.PROFESSOR_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class ProfessorNotFoundException extends CustomException {
	public ProfessorNotFoundException() {
		super(PROFESSOR_NOT_FOUND);
	}
}
