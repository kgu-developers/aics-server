package kgu.developers.api.post.presentation.exception;

import kgu.developers.common.exception.CustomException;
import kgu.developers.common.exception.ExceptionCode;

public class UnauthorizedAuthorException extends CustomException {
	public UnauthorizedAuthorException(ExceptionCode code) {
		super(code);
	}
}
