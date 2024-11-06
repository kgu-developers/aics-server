package kgu.developers.api.about.presentation.Exception;

import static kgu.developers.api.about.presentation.Exception.AboutExceptionCode.ABOUT_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class AboutNotFoundException extends CustomException {
	public AboutNotFoundException() {
		super(ABOUT_NOT_FOUND);
	}
}
