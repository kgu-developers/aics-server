package kgu.developers.api.user.presentation.exception;

import kgu.developers.common.exception.CustomException;

public class UserIdDuplicateException extends CustomException {
	public UserIdDuplicateException() {
		super(UserExceptionCode.USER_ID_DUPLICATED);
	}
}
