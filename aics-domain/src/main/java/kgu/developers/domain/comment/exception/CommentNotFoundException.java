package kgu.developers.domain.comment.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.domain.comment.exception.CommentDomainExceptionCode.COMMENT_NOT_FOUND;

public class CommentNotFoundException extends CustomException {
	public CommentNotFoundException() {
		super(COMMENT_NOT_FOUND);
	}
}
