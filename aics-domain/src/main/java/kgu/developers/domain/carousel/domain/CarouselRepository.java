package kgu.developers.domain.carousel.domain;

import java.util.List;

public interface CarouselRepository {
	Carousel save(Carousel carousel);

	void deleteById(Long id);

	List<Carousel> findAllByFileIsNotNullOrderByCreatedAtDesc();
}
