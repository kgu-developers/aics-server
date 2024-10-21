package kgu.developers.apis.api.user.presentation.exception;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_PHONE_NUMBER_DUPLICATED;
import kgu.developers.core.common.exception.CustomException;

public class UserPhoneNumberDuplicateException extends CustomException {
	public UserPhoneNumberDuplicateException() {
		super(USER_PHONE_NUMBER_DUPLICATED);
	}
}
