package kgu.developers.domain.carousel.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.carousel.infrastructure.entity.CarouselJpaEntity;

public interface JpaCarouselRepository extends JpaRepository<CarouselJpaEntity, Long> {
	List<CarouselJpaEntity> findAllByFileIdIsNotNullOrderByCreatedAtDesc();
}
