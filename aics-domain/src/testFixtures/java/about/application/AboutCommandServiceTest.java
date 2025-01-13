package about.application;

import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.CURRICULUM;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.application.command.AboutCommandService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.CategoryNotMatchException;
import mock.TestContainer;

public class AboutCommandServiceTest {
	private AboutCommandService aboutCommandService;
	private AboutRepository aboutRepository;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		aboutRepository = testContainer.aboutRepository;
		aboutCommandService = new AboutCommandService(aboutRepository);

		aboutRepository.save(
			About.builder()
				.mainCategory(DEPT_INTRO)
				.subCategory(HISTORY)
				.detailCategory("detailCategory")
				.content("about content")
				.build()
		);
	}

	@Test
	@DisplayName("createAbout은 About 객체를 생성한다")
	public void createAbout_Success() {
		// given
		MainCategory mainCategory = DEPT_INTRO;
		SubCategory subCategory = HISTORY;
		String detail = "testDetail";
		String content = "testContent";

		// when
		aboutCommandService.createAbout(mainCategory, subCategory, detail, content);

		// then
		About result = aboutRepository.findById(2L).orElseThrow();
		assertEquals(mainCategory, result.getMainCategory());
		assertEquals(subCategory, result.getSubCategory());
		assertEquals(detail, result.getDetailCategory());
		assertEquals(content, result.getContent());
	}

	@Test
	@DisplayName("createAbout은 main, subCategory가 매칭이 안될 시 CategoryNotMatchException을 발생시킨다")
	public void createAbout_Failed() {
		// given
		MainCategory mainCategory = DEPT_INTRO;
		SubCategory subCategory = CURRICULUM;
		String detail = "testDetail";
		String content = "testContent";

		// when
		// then
		assertThatThrownBy(() -> aboutCommandService.createAbout(mainCategory, subCategory, detail, content))
			.isInstanceOf(CategoryNotMatchException.class)
			.hasMessage("메인 카테고리와 보조 카테고리가 일치하지 않습니다.");
	}
}
