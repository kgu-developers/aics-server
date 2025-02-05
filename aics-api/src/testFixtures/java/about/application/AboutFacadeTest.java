package about.application;

import static kgu.developers.domain.about.domain.Category.DEPT_INTRO;
import static kgu.developers.domain.about.domain.Category.DIRECTIONS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.about.application.AboutFacade;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.application.query.AboutQueryService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;
import kgu.developers.domain.about.exception.AboutNotFoundException;
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
			.category(DEPT_INTRO)
			.content("content")
			.build());
	}

	@Test
	@DisplayName("getAboutByCategory은 About을 조회한다")
	public void getAboutByCategory() {
		// given
		Category category = DEPT_INTRO;

		// when
		AboutResponse aboutResponse = aboutFacade.getAboutByCategory(category);

		// then
		assertEquals("content", aboutResponse.content());
	}

	@Test
	@DisplayName("getAbout은 존재하지 않는 카테고리로 조회 시 AboutNotFoundException을 발생시킨다")
	public void getAbout_AboutNotFound_ThrowsException() {
		// given
		Category category = DIRECTIONS;

		// when
		// then
		assertThatThrownBy(() -> aboutFacade.getAboutByCategory(category))
			.isInstanceOf(AboutNotFoundException.class);
	}
}
