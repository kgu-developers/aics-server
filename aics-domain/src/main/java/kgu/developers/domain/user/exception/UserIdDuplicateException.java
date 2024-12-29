package kgu.developers.domain.user.exception;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.*;

import kgu.developers.common.exception.CustomException;

public class UserIdDuplicateException extends CustomException {
	public UserIdDuplicateException() {
		super(USER_ID_DUPLICATED);
	}
}
