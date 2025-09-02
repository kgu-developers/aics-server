package kgu.developers.api.carousel.application;

import java.util.List;

import org.springframework.stereotype.Component;

import kgu.developers.api.carousel.presentation.response.CarouselListResponse;
import kgu.developers.api.carousel.presentation.response.CarouselResponse;
import kgu.developers.domain.carousel.application.query.CarouselQueryService;
import kgu.developers.domain.file.application.query.FileQueryService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarouselFacade {
	private final CarouselQueryService carouselQueryService;
	private final FileQueryService fileQueryService;

	public CarouselListResponse getCarousels() {
		List<CarouselResponse> carousels = carouselQueryService.getAllCarousels()
			.stream()
			.map(carousel -> {
				Long fileId = carousel.getFileId();
				String filePath = (fileId != null) ? fileQueryService.getFilePhysicalPath(fileId) : null;
				return CarouselResponse.of(carousel, filePath);
			})
			.toList();

		return CarouselListResponse.from(carousels);
	}
}

