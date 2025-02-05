package kgu.developers.domain.about.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;

public interface JpaAboutRepository extends JpaRepository<About, Long> {
	Optional<About> findByCategory(Category category);
}
