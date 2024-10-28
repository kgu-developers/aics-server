package kgu.developers.api.post.presentation.exception;

import static kgu.developers.api.post.presentation.exception.PostExceptionCode.*;

import kgu.developers.common.exception.CustomException;

public class PostNotFoundException extends CustomException {
	public PostNotFoundException() {
		super(POST_NOT_FOUND);
	}
}
