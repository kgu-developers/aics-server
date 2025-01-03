package kgu.developers.domain.post.exception;

import static kgu.developers.domain.post.exception.PostDomainExceptionCode.POST_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class PostNotFoundException extends CustomException {
	public PostNotFoundException() {
		super(POST_NOT_FOUND);
	}
}
