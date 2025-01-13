package kgu.developers.api.carousel.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.api.carousel.application.CarouselFacade;
import kgu.developers.api.carousel.presentation.response.CarouselListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carousels")
public class CarouselControllerImpl implements CarouselController {
	private final CarouselFacade carouselFacade;

	@Override
	@GetMapping
	public ResponseEntity<CarouselListResponse> getCarousels() {
		CarouselListResponse response = carouselFacade.getCarousels();
		return ResponseEntity.ok(response);
	}
}
