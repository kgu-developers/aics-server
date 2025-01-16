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

import kgu.developers.admin.about.application.AboutAdminFacade;
import kgu.developers.admin.about.presentation.request.AboutCreateRequest;
import kgu.developers.admin.about.presentation.request.AboutUpdateRequest;
import kgu.developers.admin.about.presentation.response.AboutPersistResponse;
import kgu.developers.domain.about.application.command.AboutCommandService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import kgu.developers.domain.about.exception.CategoryNotMatchException;
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
			.mainCategory(EDU_ACTIVITIES)
			.subCategory(CURRICULUM)
			.detailCategory("initDetail")
			.content("initContent")
			.build());
	}

	@Test
	@DisplayName("createAbout은 about을 생성한다")
	public void createAbout_Success() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;
		String detail = "detail";
		String content = "content";

		AboutCreateRequest request = AboutCreateRequest.builder()
			.main(main)
			.sub(sub)
			.detail(detail)
			.content(content)
			.build();

		// when
		AboutPersistResponse result = aboutAdminFacade.createAbout(request);

		// then
		assertEquals(2L, result.id());
	}

	@Test
	@DisplayName("createAbout은 메인 카테고리와 서브 카테고리의 관계가 올바르지 않은 생성 요청 시 CategoryNotMatchException을 발생시킨다")
	public void createAbout_CategoryNotMatch_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = CURRICULUM;
		String detail = "detail";
		String content = "content";

		AboutCreateRequest request = AboutCreateRequest.builder()
			.main(main)
			.sub(sub)
			.detail(detail)
			.content(content)
			.build();

		// when
		// then
		assertThatThrownBy(() -> aboutAdminFacade.createAbout(request))
			.isInstanceOf(CategoryNotMatchException.class);
	}

	@Test
	@DisplayName("updateAbout은 About의 content를 수정한다")
	public void updateAbout_Success() {
		// given
		Long id = 1L;

		AboutUpdateRequest request = AboutUpdateRequest.builder()
			.content("updateContent")
			.build();

		// when
		aboutAdminFacade.updateAbout(id, request);

		// then
		About about = fakeAboutRepository.findById(id).orElseThrow();
		assertEquals(request.content(), about.getContent());
	}

	@Test
	@DisplayName("updateAbout은 존재하지 않는 id로 수정 요청 시 AboutNotFoundException을 발생시킨다")
	public void updateAbout_AboutNotFound_ThrowsException() {
		// given
		Long id = 0L;

		AboutUpdateRequest request = AboutUpdateRequest.builder()
			.content("updateContent")
			.build();

		// when
		// then
		assertThatThrownBy(() -> aboutAdminFacade.updateAbout(id, request))
			.isInstanceOf(AboutNotFoundException.class);
	}
}
