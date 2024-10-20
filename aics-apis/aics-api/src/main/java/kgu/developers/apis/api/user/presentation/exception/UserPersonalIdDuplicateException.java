package kgu.developers.apis.api.user.presentation.exception;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_PERSONAL_ID_DUPLICATED;
import kgu.developers.core.common.exception.CustomException;

public class UserPersonalIdDuplicateException extends CustomException {
	public UserPersonalIdDuplicateException() {
		super(USER_PERSONAL_ID_DUPLICATED);
	}
}
