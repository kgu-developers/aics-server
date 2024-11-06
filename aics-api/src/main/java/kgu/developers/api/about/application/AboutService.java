package kgu.developers.api.about.application;

import static kgu.developers.domain.about.domain.SubCategory.CLUB_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.CURRICULUM;
import static kgu.developers.domain.about.domain.SubCategory.DEPT_INTRO;
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

@Service
@RequiredArgsConstructor
public class AboutService {
	private final AboutRepository aboutRepository;

	@Transactional
	public AboutPersistResponse createAbout(AboutRequest request) {
		MainCategory mainCategory = MainCategory.valueOf(request.main().toUpperCase());
		SubCategory subCategory = SubCategory.valueOf(request.sub().toUpperCase());
		isCategoryMatch(mainCategory, subCategory);

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
		isCategoryMatch(mainCategory, subCategory);

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

	private void isCategoryMatch(MainCategory mainCategory, SubCategory subCategory) {
		switch (mainCategory) {
			case DEPT_INTRO:
				if (subCategory.equals(DEPT_INTRO)
					|| subCategory.equals(HISTORY)
					|| subCategory.equals(EDU_ENVIRONMENT)
					|| subCategory.equals(EDU_OBJECTIVES)) {
					return;
				}

			case EDU_ACTIVITIES:
				if (subCategory.equals(CURRICULUM)
					|| subCategory.equals(LEARNING_ACTIVITIES)
					|| subCategory.equals(CLUB_INTRO)) {
					return;
				}
		}
		throw new CategoryNotMatchException();
	}
}
