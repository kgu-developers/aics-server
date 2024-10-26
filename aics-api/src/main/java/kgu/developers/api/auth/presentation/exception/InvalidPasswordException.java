package kgu.developers.api.auth.presentation.exception;

import kgu.developers.common.exception.CustomException;

public class InvalidPasswordException extends CustomException {
    public InvalidPasswordException() {
        super(AuthExceptionCode.INVALID_PASSWORD);
    }
}
