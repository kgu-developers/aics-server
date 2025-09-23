package kgu.developers.domain.about.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.Category;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AboutRepositoryImpl implements AboutRepository {
	private final JpaAboutRepository jpaAboutRepository;

	@Override
	public About save(About about) {
		AboutJpaEntity entity = AboutJpaEntity.toEntity(about);
		AboutJpaEntity savedEntity = jpaAboutRepository.save(entity);
		return savedEntity.toDomain();

	}

	@Override
	public Optional<About> findByCategory(Category category) {
		Optional<AboutJpaEntity> optionalEntity = jpaAboutRepository.findByCategory(category);
		return optionalEntity.map(AboutJpaEntity::toDomain);
	}

	@Override
	public Optional<About> findById(Long id) {
		Optional<AboutJpaEntity> optionalEntity = jpaAboutRepository.findById(id);
		return optionalEntity.map(AboutJpaEntity::toDomain);
	}
}
