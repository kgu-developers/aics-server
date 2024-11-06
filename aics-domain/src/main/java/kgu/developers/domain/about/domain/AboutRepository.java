package kgu.developers.domain.about.domain;

import java.util.Optional;

public interface AboutRepository {
	About save(About about);

	Optional<About> findByMainAndSubAndDetail(MainCategory main, SubCategory sub, String detail);

	Optional<About> findByMainAndSub(MainCategory main, SubCategory sub);

	Optional<About> findById(Long id);
}
