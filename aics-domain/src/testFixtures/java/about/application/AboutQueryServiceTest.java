package about.application;

import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.application.query.AboutQueryService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import kgu.developers.domain.about.exception.CategoryNotMatchException;
import mock.repository.FakeAboutRepository;

public class AboutQueryServiceTest {
	private AboutQueryService aboutQueryService;

	@BeforeEach
	public void init() {
		FakeAboutRepository fakeAboutRepository = new FakeAboutRepository();
		aboutQueryService = new AboutQueryService(fakeAboutRepository);

		fakeAboutRepository.save(
			About.create(DEPT_INTRO, HISTORY, "detail category", "about content")
		);
	}

	@Test
	@DisplayName("getAbout은 main, subCategory와 detail이 일치하면 About을 반환한다")
	public void getAbout_Success() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;
		String detail = "detail category";

		// when
		About about = aboutQueryService.getAbout(main, sub, detail);

		// then
		assertEquals(main, about.getMainCategory());
		assertEquals(sub, about.getSubCategory());
		assertEquals(detail, about.getDetailCategory());
		assertEquals("about content", about.getContent());
	}

	@Test
	@DisplayName("getAbout은 main, subCategory와 detail이 일치하지 않으면 AboutNotFoundException을 발생시킨다")
	public void getAbout_NotFound_ThrowsException() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;
		String detail = "nonexistentDetail";

		// when & then
		assertThatThrownBy(() -> aboutQueryService.getAbout(main, sub, detail)).isInstanceOf(
			AboutNotFoundException.class).hasMessageContaining("해당 소개글을 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("getAbout은 detail이 null 또는 blank여도 요청 시 올바른 About을 반환한다")
	public void getAbout_WithNullOrBlankDetail_Success() {
		// given
		MainCategory main = DEPT_INTRO;
		SubCategory sub = HISTORY;

		// when
		About result = aboutQueryService.getAbout(main, sub, null);

		// then
		assertEquals(main, result.getMainCategory());
		assertEquals(sub, result.getSubCategory());
		assertEquals("detail category", result.getDetailCategory());
		assertEquals("about content", result.getContent());
	}

	@Test
	@DisplayName("getAbout은 main, subCategory의 매칭이 잘못된 경우 CategoryNotMatchException을 발생시킨다")
	public void getAbout_CategoryNotMatch_ThrowsException() {
		// given
		MainCategory main = MainCategory.DEPT_INTRO;
		SubCategory sub = SubCategory.CURRICULUM;
		String detail = "someDetail";

		// when & then
		assertThatThrownBy(() -> aboutQueryService.getAbout(main, sub, detail)).isInstanceOf(
			CategoryNotMatchException.class).hasMessageContaining("메인 카테고리와 보조 카테고리가 일치하지 않습니다.");
	}
}
