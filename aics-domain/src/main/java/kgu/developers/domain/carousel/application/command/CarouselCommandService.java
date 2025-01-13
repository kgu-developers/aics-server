package kgu.developers.domain.carousel.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.carousel.domain.CarouselRepository;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarouselCommandService {
	private final FileQueryService fileQueryService;
	private final CarouselRepository carouselRepository;

	public Long createCarousel(Long fileId, String text, String link) {
		FileEntity file = fileQueryService.getFileById(fileId);
		Carousel carousel = Carousel.create(text, link, file);
		return carouselRepository.save(carousel).getId();
	}

	public void deleteCarouselById(Long id) {
		carouselRepository.deleteById(id);
	}
}
