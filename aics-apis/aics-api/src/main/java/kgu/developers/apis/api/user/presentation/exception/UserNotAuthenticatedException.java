package kgu.developers.apis.api.user.presentation.exception;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_NOT_AUTHENTICATED;

import kgu.developers.core.common.exception.CustomException;

public class UserNotAuthenticatedException extends CustomException {

	public UserNotAuthenticatedException() {
		super(USER_NOT_AUTHENTICATED);
	}
}
