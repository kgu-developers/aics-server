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
		MainCategory mainCategory = MainCategory.valueOf(request.main().toUpperCase());
		SubCategory subCategory = SubCategory.valueOf(request.sub().toUpperCase());
		categoryMatchCheck(mainCategory, subCategory);

		String detail = subCategory.equals(CURRICULUM) ? request.detail() : "";

		Long id = aboutRepository.save(
			About.create(mainCategory, subCategory, detail, request.content())
		).getId();

		return AboutPersistResponse.of(id);
	}

	@Transactional(readOnly = true)
	public AboutResponse getAbout(String main, String sub, String detail) {
		MainCategory mainCategory = MainCategory.valueOf(main.toUpperCase());
		SubCategory subCategory = SubCategory.valueOf(sub.toUpperCase());
		categoryMatchCheck(mainCategory, subCategory);

		About about = subCategory.equals(CURRICULUM)
			? aboutRepository.findByMainAndSubAndDetail(mainCategory, subCategory, detail)
			.orElseThrow(AboutNotFoundException::new)
			: aboutRepository.findByMainAndSub(mainCategory, subCategory)
			.orElseThrow(AboutNotFoundException::new);

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
