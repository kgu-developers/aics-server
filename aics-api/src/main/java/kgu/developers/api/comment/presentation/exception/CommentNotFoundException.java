package kgu.developers.api.comment.presentation.exception;

import kgu.developers.common.exception.CustomException;

import static kgu.developers.api.comment.presentation.exception.CommentExceptionCode.COMMENT_NOT_FOUND_EXCEPTION;

public class CommentNotFoundException extends CustomException {
	public CommentNotFoundException() {
		super(COMMENT_NOT_FOUND_EXCEPTION);
	}
}
