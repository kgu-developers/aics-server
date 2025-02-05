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
		return jpaAboutRepository.save(about);
	}

	@Override
	public Optional<About> findByCategory(Category category) {
		return jpaAboutRepository.findByCategory(category);
	}

	@Override
	public Optional<About> findById(Long id) {
		return jpaAboutRepository.findById(id);
	}
}
