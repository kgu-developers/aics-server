package kgu.developers.domain.about.application.query;

import org.springframework.stereotype.Service;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.Category;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AboutQueryService {
	private final AboutRepository aboutRepository;

	public About getAboutByCategory(Category category) {
		return aboutRepository.findByCategory(category)
			.orElseThrow(AboutNotFoundException::new);
	}
}
