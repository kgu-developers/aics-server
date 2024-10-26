package kgu.developers.api.user.presentation.exception;

import kgu.developers.common.exception.CustomException;

public class UserNotAuthenticatedException extends CustomException {

	public UserNotAuthenticatedException() {
		super(UserExceptionCode.USER_NOT_AUTHENTICATED);
	}
}
