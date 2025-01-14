package kgu.developers.admin.carousel.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;
import kgu.developers.domain.carousel.application.command.CarouselCommandService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarouselAdminFacade {
	private final CarouselCommandService carouselCommandService;

	@Transactional
	public CarouselPersistResponse createCarousel(Long fileId, CarouselRequest request) {
		Long id = carouselCommandService.createCarousel(fileId, request.text(), request.link());
		return CarouselPersistResponse.of(id);
	}

	public void deleteCarousel(Long id) {
		carouselCommandService.deleteCarouselById(id);
	}
}
