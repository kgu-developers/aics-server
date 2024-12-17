package kgu.developers.api.auth.presentation.exception;

import static kgu.developers.api.auth.presentation.exception.AuthExceptionCode.TOKEN_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class TokenNotFoundException extends CustomException {
	public TokenNotFoundException() {
		super(TOKEN_NOT_FOUND);
	}
}
