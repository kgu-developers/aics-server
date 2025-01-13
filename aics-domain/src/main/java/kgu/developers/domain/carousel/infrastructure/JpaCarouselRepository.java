package kgu.developers.domain.carousel.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.carousel.domain.Carousel;

public interface JpaCarouselRepository extends JpaRepository<Carousel, Long> {
}
