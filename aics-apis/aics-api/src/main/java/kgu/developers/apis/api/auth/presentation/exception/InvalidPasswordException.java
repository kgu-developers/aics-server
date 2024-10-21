package kgu.developers.apis.api.auth.presentation.exception;

import static kgu.developers.apis.api.auth.presentation.exception.AuthExceptionCode.INVALID_PASSWORD;

import kgu.developers.core.common.exception.CustomException;

public class InvalidPasswordException extends CustomException {
    public InvalidPasswordException() {
        super(INVALID_PASSWORD);
    }
}
