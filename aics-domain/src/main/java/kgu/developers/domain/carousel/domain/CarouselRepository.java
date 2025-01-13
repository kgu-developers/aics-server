package kgu.developers.domain.carousel.domain;

public interface CarouselRepository {
	Carousel save(Carousel carousel);

	void deleteById(Long id);
}
