package about.application;

import static kgu.developers.domain.about.domain.Category.DEPT_INTRO;
import static kgu.developers.domain.about.domain.Category.DIRECTIONS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.application.command.AboutCommandService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import mock.repository.FakeAboutRepository;

public class AboutCommandServiceTest {
	private AboutCommandService aboutCommandService;
	private FakeAboutRepository fakeAboutRepository;

	@BeforeEach
	public void init() {
		fakeAboutRepository = new FakeAboutRepository();
		aboutCommandService = new AboutCommandService(fakeAboutRepository);

		fakeAboutRepository.save(
			About.create(DEPT_INTRO, "intro content")
		);
	}

	@Test
	@DisplayName("createAbout은 About 객체를 생성한다")
	public void createAbout_Success() {
		// given
		String content = "test content";

		// when
		Long result = aboutCommandService.createAbout(DEPT_INTRO, content);

		// then
		assertEquals(2L, result);
	}

	@Test
	@DisplayName("updateAbout은 About을 수정할 수 있다")
	public void updateAbout_Success() {
		// given
		Category category = DEPT_INTRO;
		String newContent = "update content";

		// when
		aboutCommandService.updateAbout(category, newContent);

		// then
		About about = fakeAboutRepository.findByCategory(category).orElse(null);

		assertNotNull(about);
		assertEquals(category, about.getCategory());
		assertEquals(newContent, about.getContent());
	}

	@Test
	@DisplayName("updateAbout은 존재하지 않는 about 수정 요청 시 AboutNotFoundException을 발생시킨다")
	public void updateAbout_Throws_AboutNotFoundException() {
		// given
		String newContent = "update content";

		// when
		// then
		assertThatThrownBy(() -> aboutCommandService.updateAbout(DIRECTIONS, newContent))
			.isInstanceOf(AboutNotFoundException.class);
	}
}
