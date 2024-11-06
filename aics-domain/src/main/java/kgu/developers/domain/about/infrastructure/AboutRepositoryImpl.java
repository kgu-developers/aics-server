package kgu.developers.domain.about.infrastructure;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AboutRepositoryImpl implements AboutRepository {
	private final JpaAboutRepository jpaAboutRepository;

	@Override
	public About save(About about) {
		return jpaAboutRepository.save(about);
	}

	@Override
	public Optional<About> findByMainCategoryAndSubCategoryAndDetailCategory(MainCategory mainCategory, SubCategory subCategory, String detailCategory) {
		return jpaAboutRepository.findByMainCategoryAndSubCategoryAndDetailCategory(mainCategory, subCategory, detailCategory);
	}

	@Override
	public Optional<About> findByMainCategoryAndSubCategory(MainCategory mainCategory, SubCategory subCategory) {
		return jpaAboutRepository.findByMainCategoryAndSubCategory(mainCategory, subCategory);
	}
}
