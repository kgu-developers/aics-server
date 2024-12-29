package kgu.developers.domain.user.exception;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.*;

import kgu.developers.common.exception.CustomException;

public class UserNotAuthenticatedException extends CustomException {

	public UserNotAuthenticatedException() {
		super(USER_NOT_AUTHENTICATED);
	}
}
