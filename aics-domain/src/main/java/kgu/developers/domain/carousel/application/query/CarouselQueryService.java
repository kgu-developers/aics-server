package kgu.developers.domain.carousel.application.query;

import org.springframework.stereotype.Service;

import kgu.developers.domain.carousel.domain.CarouselRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarouselQueryService {
	private final CarouselRepository carouselRepository;
}
