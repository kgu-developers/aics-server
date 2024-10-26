package kgu.developers.domain.user.exception;

import kgu.developers.common.exception.CustomException;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException() {
		super(UserDomainExceptionCode.USER_NOT_FOUND);
	}
}
