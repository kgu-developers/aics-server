package kgu.developers.domain.user.exception;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.DEPARTMENT_CODE_NOT_VALID;

import kgu.developers.common.exception.CustomException;

public class DepartmentCodeNotValidException extends CustomException {
	public DepartmentCodeNotValidException() {
		super(DEPARTMENT_CODE_NOT_VALID);
	}
}
