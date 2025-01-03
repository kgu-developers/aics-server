package kgu.developers.domain.about.exception;

import static kgu.developers.domain.about.exception.AboutDomainExceptionCode.CATEGORY_NOT_MATCH;

import kgu.developers.common.exception.CustomException;

public class CategoryNotMatchException extends CustomException {
	public CategoryNotMatchException() {
		super(CATEGORY_NOT_MATCH);
	}
}
