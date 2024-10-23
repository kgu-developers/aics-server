package kgu.developers.apis.api.user.presentation.exception;

import kgu.developers.core.common.exception.CustomException;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_ID_DUPLICATED;

public class UserIdDuplicateException extends CustomException {
	public UserIdDuplicateException() {
		super(USER_ID_DUPLICATED);
	}
}
