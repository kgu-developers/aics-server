package kgu.developers.domain.about.application.query;

import static kgu.developers.domain.about.domain.About.categoryMatchCheck;

import org.springframework.stereotype.Service;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AboutQueryService {
	private final AboutRepository aboutRepository;

	public About getAbout(MainCategory main, SubCategory sub) {
		categoryMatchCheck(main, sub);
		return aboutRepository.findByMainAndSub(main, sub)
			.orElseThrow(AboutNotFoundException::new);
	}
}
