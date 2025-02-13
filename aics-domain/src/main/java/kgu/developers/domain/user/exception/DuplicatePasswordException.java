package kgu.developers.domain.user.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.DUPLICATE_PASSWORD;

public class DuplicatePasswordException extends CustomException {
	public DuplicatePasswordException() {
		super(DUPLICATE_PASSWORD);
	}
}
