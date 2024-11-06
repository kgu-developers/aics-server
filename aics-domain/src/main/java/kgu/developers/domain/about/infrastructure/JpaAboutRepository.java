package kgu.developers.domain.about.infrastructure;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAboutRepository extends JpaRepository<About, Long> {
	Optional<About> findByMainCategoryAndSubCategoryAndDetailCategory(MainCategory mainCategory, SubCategory subCategory, String detailCategory);

	Optional<About> findByMainCategoryAndSubCategory(MainCategory mainCategory, SubCategory subCategory);
}
