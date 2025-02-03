package kgu.developers.domain.about.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;

public interface JpaAboutRepository extends JpaRepository<About, Long> {
	Optional<About> findByMainCategoryAndSubCategory(MainCategory mainCategory, SubCategory subCategory);
}
