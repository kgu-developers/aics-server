package kgu.developers.domain.user.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.NOT_DELETABLE_USER;

public class NotDeletableUserException extends CustomException {

	public NotDeletableUserException() {
		super(NOT_DELETABLE_USER);
	}
}
