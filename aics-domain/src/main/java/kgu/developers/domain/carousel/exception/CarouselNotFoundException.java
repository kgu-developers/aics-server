package kgu.developers.domain.carousel.exception;

import static kgu.developers.domain.carousel.exception.CarouselDomainExceptionCode.CAROUSEL_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class CarouselNotFoundException extends CustomException {
	public CarouselNotFoundException() {
		super(CAROUSEL_NOT_FOUND);
	}
}
