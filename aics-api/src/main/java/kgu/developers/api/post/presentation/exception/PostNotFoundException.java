package kgu.developers.api.post.presentation.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.api.post.presentation.exception.PostExceptionCode.POST_NOT_FOUND;

public class PostNotFoundException extends CustomException {
	public PostNotFoundException() {
		super(POST_NOT_FOUND);
	}
}
