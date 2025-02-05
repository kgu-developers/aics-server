package about.application;

import static kgu.developers.domain.about.domain.Category.DEPT_INTRO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.application.query.AboutQueryService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;
import mock.repository.FakeAboutRepository;

public class AboutQueryServiceTest {
	private AboutQueryService aboutQueryService;

	@BeforeEach
	public void init() {
		FakeAboutRepository fakeAboutRepository = new FakeAboutRepository();
		aboutQueryService = new AboutQueryService(fakeAboutRepository);

		fakeAboutRepository.save(
			About.create(DEPT_INTRO, "content")
		);
	}

	@Test
	@DisplayName("getAbout은 해당 category의 About을 반환한다")
	public void getAbout_Success() {
		// given
		Category category = DEPT_INTRO;

		// when
		About about = aboutQueryService.getAboutByCategory(category);

		// then
		assertEquals(category, about.getCategory());
		assertEquals("content", about.getContent());
	}
}
