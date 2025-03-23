package kgu.developers.domain.carousel.domain;

import java.util.List;
import java.util.Optional;

public interface CarouselRepository {
	Carousel save(Carousel carousel);

	void deleteById(Long id);

	List<Carousel> findAllByFileIsNotNullOrderByCreatedAtDesc();

	Optional<Carousel> findById(Long id);
}
