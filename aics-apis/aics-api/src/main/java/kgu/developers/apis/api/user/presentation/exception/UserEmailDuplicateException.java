package kgu.developers.apis.api.user.presentation.exception;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_EMAIL_ID_DUPLICATED;
import kgu.developers.core.common.exception.CustomException;

public class UserEmailDuplicateException extends CustomException {
	public UserEmailDuplicateException() {
		super(USER_EMAIL_ID_DUPLICATED);
	}
}
