package kgu.developers.api.about.application;


import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.MainCategory.EDU_ACTIVITIES;
import static kgu.developers.domain.about.domain.SubCategory.CLUB_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.CURRICULUM;
import static kgu.developers.domain.about.domain.SubCategory.EDU_ENVIRONMENT;
import static kgu.developers.domain.about.domain.SubCategory.EDU_OBJECTIVES;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static kgu.developers.domain.about.domain.SubCategory.LEARNING_ACTIVITIES;

import kgu.developers.api.about.presentation.Exception.AboutNotFoundException;
import kgu.developers.api.about.presentation.Exception.CategoryNotMatchException;
import kgu.developers.api.about.presentation.request.AboutRequest;
import kgu.developers.api.about.presentation.response.AboutPersistResponse;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AboutService {
	private final AboutRepository aboutRepository;

	private static final Map<MainCategory, Set<SubCategory>> CATEGORY_MAP = new EnumMap<>(MainCategory.class);

	static {
		CATEGORY_MAP.put(DEPT_INTRO, Set.of(SubCategory.DEPT_INTRO, HISTORY, EDU_ENVIRONMENT, EDU_OBJECTIVES));
		CATEGORY_MAP.put(EDU_ACTIVITIES, Set.of(CURRICULUM, LEARNING_ACTIVITIES, CLUB_INTRO));
	}

	@Transactional
	public AboutPersistResponse createAbout(AboutRequest request) {
		categoryMatchCheck(request.main(), request.sub());

		About about = About.create(request.main(), request.sub(), request.detail(), request.content());
		Long id = aboutRepository.save(about).getId();

		return AboutPersistResponse.of(id);
	}

	@Transactional(readOnly = true)
	public AboutResponse getAbout(MainCategory main, SubCategory sub, String detail) {
		categoryMatchCheck(main, sub);

		System.out.println(detail);
		About about;
		if (detail == null || detail.isBlank()) {
			about = aboutRepository.findByMainAndSub(main, sub)
				.orElseThrow(AboutNotFoundException::new);
		} else {
			about = aboutRepository.findByMainAndSubAndDetail(main, sub, detail)
				.orElseThrow(AboutNotFoundException::new);
		}

		return AboutResponse.from(about);
	}

	@Transactional
	public void updateAbout(Long id, AboutRequest request) {
		About about = aboutRepository.findById(id)
			.orElseThrow(AboutNotFoundException::new);

		about.updateContent(request.content());
	}

	private void categoryMatchCheck(MainCategory mainCategory, SubCategory subCategory) {
		if (CATEGORY_MAP.getOrDefault(mainCategory, Set.of()).contains(subCategory))
			return;

		throw new CategoryNotMatchException();
	}
}
