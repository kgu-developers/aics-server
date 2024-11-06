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
	public Optional<About> findByMainAndSubAndDetail(MainCategory main, SubCategory sub, String detail) {
		return jpaAboutRepository.findByMainCategoryAndSubCategoryAndDetailCategory(main, sub, detail);
	}

	@Override
	public Optional<About> findByMainAndSub(MainCategory main, SubCategory sub) {
		return jpaAboutRepository.findByMainCategoryAndSubCategory(main, sub);
	}

	@Override
	public Optional<About> findById(Long id) {
		return jpaAboutRepository.findById(id);
	}
}
