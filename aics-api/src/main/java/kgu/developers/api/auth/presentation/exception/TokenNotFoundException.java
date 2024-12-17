package kgu.developers.api.auth.presentation.exception;

import static kgu.developers.api.auth.presentation.exception.AuthExceptionCode.TOKEN_NOT_FOUND;

import kgu.developers.common.exception.CustomException;
import kgu.developers.common.exception.ExceptionCode;

public class TokenNotFoundException extends CustomException {
	public TokenNotFoundException(ExceptionCode code) {
		super(TOKEN_NOT_FOUND);
	}
}
