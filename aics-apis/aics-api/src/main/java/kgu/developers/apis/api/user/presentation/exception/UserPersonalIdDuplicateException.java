package kgu.developers.apis.api.user.presentation.exception;

import static kgu.developers.apis.api.user.presentation.exception.UserExceptionCode.USER_PERSONAL_ID_DUPLICATED;
import static org.springframework.http.HttpStatus.CONFLICT;
import kgu.developers.core.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(CONFLICT)
public class UserPersonalIdDuplicateException extends CustomException {
	private static final Logger logger = LoggerFactory.getLogger(UserPersonalIdDuplicateException.class);

	public UserPersonalIdDuplicateException() {
		super(USER_PERSONAL_ID_DUPLICATED);
		logger.error(USER_PERSONAL_ID_DUPLICATED.getMessage());
	}
}
