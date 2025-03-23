package kgu.developers.admin.carousel.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.request.CarouselUpdateRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;
import kgu.developers.domain.carousel.application.command.CarouselCommandService;
import kgu.developers.domain.carousel.application.query.CarouselQueryService;
import kgu.developers.domain.carousel.domain.Carousel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarouselAdminFacade {
	private final CarouselCommandService carouselCommandService;
	private final CarouselQueryService carouselQueryService;

	@Transactional
	public CarouselPersistResponse createCarousel(Long fileId, CarouselRequest request) {
		Long id = carouselCommandService.createCarousel(fileId, request.text(), request.link());
		return CarouselPersistResponse.of(id);
	}

	@Transactional
	public void updateCarousel(Long id, CarouselUpdateRequest request) {
		Carousel carousel = carouselQueryService.getById(id);
		carouselCommandService.updateCarousel(carousel, request.link(), request.text(), request.fileId());
	}

	public void deleteCarousel(Long id) {
		carouselCommandService.deleteCarouselById(id);
	}
}
