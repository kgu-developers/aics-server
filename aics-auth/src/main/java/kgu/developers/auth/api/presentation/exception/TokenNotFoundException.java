package kgu.developers.auth.api.presentation.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.auth.api.presentation.exception.AuthExceptionCode.TOKEN_NOT_FOUND;

public class TokenNotFoundException extends CustomException {
	public TokenNotFoundException() {
		super(TOKEN_NOT_FOUND);
	}
}
