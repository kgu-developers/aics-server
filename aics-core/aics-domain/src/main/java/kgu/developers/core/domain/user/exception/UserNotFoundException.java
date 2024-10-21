package kgu.developers.core.domain.user.exception;

import static kgu.developers.core.domain.user.exception.UserDomainExceptionCode.USER_NOT_FOUND;

import kgu.developers.core.common.exception.CustomException;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}
}
