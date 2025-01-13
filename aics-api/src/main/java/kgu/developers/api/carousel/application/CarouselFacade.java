package kgu.developers.api.carousel.application;

import java.util.List;

import org.springframework.stereotype.Component;

import kgu.developers.api.carousel.presentation.response.CarouselListResponse;
import kgu.developers.domain.carousel.application.query.CarouselQueryService;
import kgu.developers.domain.carousel.domain.Carousel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarouselFacade {
	private final CarouselQueryService carouselQueryService;

	public CarouselListResponse getCarousels() {
		List<Carousel> carousels = carouselQueryService.getAllCarousels();
		return CarouselListResponse.from(carousels);
	}
}
