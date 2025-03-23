package kgu.developers.domain.carousel.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.carousel.domain.CarouselRepository;
import kgu.developers.domain.carousel.exception.CarouselNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarouselQueryService {
	private final CarouselRepository carouselRepository;

	public List<Carousel> getAllCarousels() {
		return carouselRepository.findAllByFileIsNotNullOrderByCreatedAtDesc();
	}

	public Carousel getById(Long id) {
		return carouselRepository.findById(id)
			.orElseThrow(CarouselNotFoundException::new);
	}
}
