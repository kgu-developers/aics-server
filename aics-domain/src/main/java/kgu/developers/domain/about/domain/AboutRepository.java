package kgu.developers.domain.about.domain;

import java.util.Optional;

public interface AboutRepository {
	About save(About about);

	Optional<About> findByMainCategoryAndSubCategoryAndDetailCategory(MainCategory mainCategory,
																	  SubCategory subCategory,
																	  String detailCategory);

	Optional<About> findByMainCategoryAndSubCategory(MainCategory mainCategory,
													 SubCategory subCategory);

}
