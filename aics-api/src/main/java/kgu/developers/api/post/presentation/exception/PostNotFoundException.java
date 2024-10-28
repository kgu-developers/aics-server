package kgu.developers.api.post.presentation.exception;

import kgu.developers.common.exception.CustomException;
import kgu.developers.common.exception.ExceptionCode;

public class PostNotFoundException extends CustomException {
	public PostNotFoundException(ExceptionCode code) {
		super(code);
	}
}
