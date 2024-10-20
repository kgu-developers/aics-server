package kgu.developers.apis.api.user.presentation.exception;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_PERSONAL_ID_DUPLICATED;
import static org.springframework.http.HttpStatus.CONFLICT;
import kgu.developers.core.common.exception.CustomException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(CONFLICT)
public class UserPersonalIdDuplicateException extends CustomException {
	public UserPersonalIdDuplicateException() {
		super(USER_PERSONAL_ID_DUPLICATED);
	}
}
