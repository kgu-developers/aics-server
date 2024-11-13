package kgu.developers.domain.user.exception;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.DEPT_CODE_NOT_VALID;

import kgu.developers.common.exception.CustomException;

public class DeptCodeNotValidException extends CustomException {
	public DeptCodeNotValidException() {
		super(DEPT_CODE_NOT_VALID);
	}
}
