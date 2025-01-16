package about.application;

import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.MainCategory.EDU_ACTIVITIES;
import static kgu.developers.domain.about.domain.SubCategory.CURRICULUM;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.about.application.AboutFacade;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.application.query.AboutQueryService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import kgu.developers.domain.about.exception.CategoryNotMatchException;
import mock.repository.FakeAboutRepository;

public class AboutFacadeTest {
	private AboutFacade aboutFacade;

	@BeforeEach
	public void init() {
		FakeAboutRepository fakeAboutRepository = new FakeAboutRepository();
		this.aboutFacade = new AboutFacade(
			new AboutQueryService(fakeAboutRepository)
		);

		fakeAboutRepository.save(About.builder()
			.mainCategory(EDU_ACTIVITIES)
			.subCategory(CURRICULUM)
			.detailCategory("initDetail")
			.content("initContent")
			.build());
	}

	@Test
	@DisplayName("getAbout은 About을 조회한다")
	public void getAbout_Success() {
		// given
		MainCategory main = EDU_ACTIVITIES;
		SubCategory sub = CURRICULUM;
		String detail = "initDetail";

		// when
		AboutResponse aboutResponse = aboutFacade.getAbout(main, sub, detail);

		// then
		assertEquals("initContent", aboutResponse.content());
	}

	@Test
	@DisplayName("getAbout은 메인 카테고리와 서브 카테고리의 관계가 올바르지 않을 시 CategoryNotMatchException을 발생시킨다")
	public void getAbout_CategoryNotMatch_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = CURRICULUM;
		String detail = "failDetail";

		// when
		// then
		assertThatThrownBy(() -> aboutFacade.getAbout(main, sub, detail))
			.isInstanceOf(CategoryNotMatchException.class);
	}

	@Test
	@DisplayName("getAbout은 존재하지 않는 카테고리로 조회 시 AboutNotFoundException을 발생시킨다")
	public void getAbout_AboutNotFound_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;
		String detail = "failDetail";

		// when
		// then
		assertThatThrownBy(() -> aboutFacade.getAbout(main, sub, null))
			.isInstanceOf(AboutNotFoundException.class);

		assertThatThrownBy(() -> aboutFacade.getAbout(main, sub, detail))
			.isInstanceOf(AboutNotFoundException.class);
	}
}
