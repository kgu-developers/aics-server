package kgu.developers.domain.carousel.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.carousel.domain.CarouselRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CarouselRepositoryImpl implements CarouselRepository {
	private final JpaCarouselRepository jpaCarouselRepository;

	@Override
	public Carousel save(Carousel carousel) {
		return jpaCarouselRepository.save(carousel);
	}

	@Override
	public void deleteById(Long id) {
		jpaCarouselRepository.deleteById(id);
	}

	@Override
	public List<Carousel> findAllByFileIsNotNullOrderByCreatedAtDesc() {
		return jpaCarouselRepository.findAllByFileIdIsNotNullOrderByCreatedAtDesc();
	}

	@Override
	public Optional<Carousel> findById(Long id) {
		return jpaCarouselRepository.findById(id);
	}
}
