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

import kgu.developers.api.about.application.AboutService;
import kgu.developers.api.about.presentation.Exception.AboutNotFoundException;
import kgu.developers.api.about.presentation.Exception.CategoryNotMatchException;
import kgu.developers.api.about.presentation.request.AboutRequest;
import kgu.developers.api.about.presentation.request.AboutUpdateRequest;
import kgu.developers.api.about.presentation.response.AboutPersistResponse;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import mock.FakeAboutRepository;

public class AboutServiceTest {
	private AboutService aboutService;

	@BeforeEach
	public void init() {
		FakeAboutRepository fakeAboutRepository = new FakeAboutRepository();
		this.aboutService = new AboutService(fakeAboutRepository);

		fakeAboutRepository.save(About.builder()
			.mainCategory(EDU_ACTIVITIES)
			.subCategory(CURRICULUM)
			.detailCategory("initDetail")
			.content("initContent")
			.build());
	}

	@Test
	@DisplayName("createAbout은 about을 생성할 수 있다.")
	public void createAbout_Success() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;
		String detail = "detail";
		String content = "content";

		AboutRequest request = AboutRequest.builder()
			.main(main)
			.sub(sub)
			.detail(detail)
			.content(content)
			.build();

		// when
		AboutPersistResponse response = aboutService.createAbout(request);

		// then
		AboutResponse aboutResponse = aboutService.getAbout(main, sub, detail);

		assertEquals(response.id(), 2L);
		assertEquals(aboutResponse.content(), content);
	}

	@Test
	@DisplayName("createAbout은 메인 카테고리와 서브 카테고리의 관계가 올바르지 않은 생성 요청 시 CategoryNotMatchException을 발생 한다.")
	public void createAbout_CategoryNotMatch_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = CURRICULUM;
		String detail = "detail";
		String content = "content";

		AboutRequest request = AboutRequest.builder()
			.main(main)
			.sub(sub)
			.detail(detail)
			.content(content)
			.build();

		// when
		// then
		assertThatThrownBy(() -> {
			aboutService.createAbout(request);
		}).isInstanceOf(CategoryNotMatchException.class);
	}

	@Test
	@DisplayName("getAbout은 About을 조회할 수 있다.")
	public void getAbout_Success() {
		// given
		MainCategory main = EDU_ACTIVITIES;
		SubCategory sub = CURRICULUM;
		String detail = "initDetail";

		// when
		AboutResponse aboutResponse = aboutService.getAbout(main, sub, detail);

		// then
		assertEquals(aboutResponse.content(), "initContent");
	}

	@Test
	@DisplayName("getAbout은 메인 카테고리와 서브 카테고리의 관계가 올바르지 않을 시 CategoryNotMatchException을 발생 한다.")
	public void getAbout_CategoryNotMatch_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = CURRICULUM;
		String detail = "failDetail";

		// when
		// then
		assertThatThrownBy(() -> {
			aboutService.getAbout(main, sub, detail);
		}).isInstanceOf(CategoryNotMatchException.class);
	}

	@Test
	@DisplayName("getAbout은 존재하지 않는 카테고리로 조회 시 AboutNotFoundException을 발생 한다.")
	public void getAbout_AboutNotFound_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;
		String detail = "failDetail";

		// when
		// then
		assertThatThrownBy(() -> {
			aboutService.getAbout(main, sub, null);
		}).isInstanceOf(AboutNotFoundException.class);

		assertThatThrownBy(() -> {
			aboutService.getAbout(main, sub, detail);
		}).isInstanceOf(AboutNotFoundException.class);
	}

	@Test
	@DisplayName("updateAbout은 About의 content를 수정할 수 있다.")
	public void updateAbout_Success() {
		// given
		Long id = 1L;

		AboutUpdateRequest request = AboutUpdateRequest.builder()
			.content("updateContent")
			.build();

		// when
		aboutService.updateAbout(id, request);

		// then
		AboutResponse response = aboutService.getAbout(EDU_ACTIVITIES, CURRICULUM, "initDetail");

		assertEquals(response.content(), "updateContent");
	}

	@Test
	@DisplayName("updateAbout은 존재하지 않는 id로 수정 요청 시 AboutNotFoundException을 발생 한다.")
	public void updateAbout_AboutNotFound_ThrowsException() {
		// given
		Long id = 0L;

		AboutUpdateRequest request = AboutUpdateRequest.builder()
			.content("updateContent")
			.build();

		// when
		// then
		assertThatThrownBy(() -> {
			aboutService.updateAbout(id, request);
		}).isInstanceOf(AboutNotFoundException.class);
	}
}
