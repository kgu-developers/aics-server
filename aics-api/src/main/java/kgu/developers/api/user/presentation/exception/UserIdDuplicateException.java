package kgu.developers.api.user.presentation.exception;

import static kgu.developers.api.user.presentation.exception.UserExceptionCode.USER_ID_DUPLICATED;

import kgu.developers.common.exception.CustomException;

public class UserIdDuplicateException extends CustomException {
	public UserIdDuplicateException() {
		super(USER_ID_DUPLICATED);
	}
}
