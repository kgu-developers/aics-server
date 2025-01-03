package kgu.developers.domain.about.exception;

import static kgu.developers.domain.about.exception.AboutDomainExceptionCode.ABOUT_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class AboutNotFoundException extends CustomException {
	public AboutNotFoundException() {
		super(ABOUT_NOT_FOUND);
	}
}
