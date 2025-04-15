package about.application;

import static kgu.developers.domain.about.domain.Category.DEPT_INTRO;
import static kgu.developers.domain.about.domain.Category.DIRECTIONS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.about.application.AboutAdminFacade;
import kgu.developers.admin.about.presentation.request.AboutCreateRequest;
import kgu.developers.admin.about.presentation.request.AboutUpdateRequest;
import kgu.developers.admin.about.presentation.response.AboutPersistResponse;
import kgu.developers.domain.about.application.command.AboutCommandService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import mock.repository.FakeAboutRepository;

public class AboutAdminFacadeTest {
	private AboutAdminFacade aboutAdminFacade;
	private FakeAboutRepository fakeAboutRepository;

	@BeforeEach
	public void init() {
		fakeAboutRepository = new FakeAboutRepository();
		aboutAdminFacade = new AboutAdminFacade(
			new AboutCommandService(fakeAboutRepository)
		);

		fakeAboutRepository.save(About.builder()
			.category(DEPT_INTRO)
			.content("content")
			.build());
	}

	@Test
	@DisplayName("createAbout은 about을 생성한다")
	public void createAbout_Success() {
		// given
		String content = "content";
		AboutCreateRequest request = AboutCreateRequest.builder()
			.category(DEPT_INTRO)
			.content(content)
			.build();

		// when
		AboutPersistResponse result = aboutAdminFacade.createAbout(request);

		// then
		assertEquals(2L, result.id());
	}

	@Test
	@DisplayName("updateAbout은 About의 content를 수정한다")
	public void updateAbout_Success() {
		// given
		Category category = DEPT_INTRO;

		AboutUpdateRequest request = AboutUpdateRequest.builder()
			.content("updateContent")
			.build();

		// when
		aboutAdminFacade.updateAbout(category, request);

		// then
		About about = fakeAboutRepository.findByCategory(category).orElseThrow();
		assertEquals(request.content(), about.getContent());
	}

	@Test
	@DisplayName("updateAbout은 존재하지 않는 id로 수정 요청 시 AboutNotFoundException을 발생시킨다")
	public void updateAbout_AboutNotFound_ThrowsException() {
		// given
		AboutUpdateRequest request = AboutUpdateRequest.builder()
			.content("updateContent")
			.build();

		// when
		// then
		assertThatThrownBy(() -> aboutAdminFacade.updateAbout(DIRECTIONS, request))
			.isInstanceOf(AboutNotFoundException.class);
	}
}
