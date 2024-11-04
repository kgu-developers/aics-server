package kgu.developers.api.professor.presentation.exception;

import static kgu.developers.api.professor.presentation.exception.ProfessorExceptionCode.PROFESSOR_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class ProfessorNotFoundException extends CustomException {
	public ProfessorNotFoundException() {
		super(PROFESSOR_NOT_FOUND);
	}
}
