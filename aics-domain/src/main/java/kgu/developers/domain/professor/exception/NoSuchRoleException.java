package kgu.developers.domain.professor.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.professor.exception.ProfessorDomainExceptionCode.NO_SUCH_ROLE;

public class NoSuchRoleException extends CustomException {
	public NoSuchRoleException() {
		super(NO_SUCH_ROLE);
	}
}
