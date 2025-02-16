package kgu.developers.domain.user.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.ALREADY_DELETED_USER;

public class AlreadyDeletedUserException extends CustomException {
	public AlreadyDeletedUserException() {
		super(ALREADY_DELETED_USER);
	}
}
