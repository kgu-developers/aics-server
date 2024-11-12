package kgu.developers.domain.user.exception;

import static kgu.developers.domain.user.exception.UserDomainExceptionCode.EMAIL_NOT_VALID;

import kgu.developers.common.exception.CustomException;

public class EmailDomainNotValidException extends CustomException {
	public EmailDomainNotValidException() {
		super(EMAIL_NOT_VALID);
	}
}
