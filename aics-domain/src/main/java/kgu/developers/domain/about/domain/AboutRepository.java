package kgu.developers.domain.about.domain;

import java.util.Optional;

public interface AboutRepository {
	About save(About about);

	Optional<About> findById(Long id);

	Optional<About> findByCategory(Category category);
}
