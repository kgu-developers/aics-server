package kgu.developers.auth.api.presentation.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.auth.api.presentation.exception.AuthExceptionCode.ALREADY_DELETED_USER;

public class AlreadyDeletedUserException extends CustomException {
	public AlreadyDeletedUserException() {
		super(ALREADY_DELETED_USER);
	}
}
