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
		AboutEntity entity = AboutEntity.fromDomain(about);
		AboutEntity savedEntity = jpaAboutRepository.save(entity);
		return savedEntity.toDomain();

	}

	@Override
	public Optional<About> findByCategory(Category category) {
		Optional<AboutEntity> optionalEntity = jpaAboutRepository.findByCategory(category);
		return optionalEntity.map(AboutEntity::toDomain);
	}

	@Override
	public Optional<About> findById(Long id) {
		Optional<AboutEntity> optionalEntity = jpaAboutRepository.findById(id);
		return optionalEntity.map(AboutEntity::toDomain);
	}
}
