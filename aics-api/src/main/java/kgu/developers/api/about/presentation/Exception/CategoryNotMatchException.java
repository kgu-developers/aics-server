package kgu.developers.api.about.presentation.Exception;

import static kgu.developers.api.about.presentation.Exception.AboutExceptionCode.CATEGORY_NOT_MATCH;

import kgu.developers.common.exception.CustomException;

public class CategoryNotMatchException extends CustomException {
	public CategoryNotMatchException() {
		super(CATEGORY_NOT_MATCH);
	}
}
