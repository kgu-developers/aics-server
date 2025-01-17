package about.application;

import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.CURRICULUM;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.application.command.AboutCommandService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import kgu.developers.domain.about.exception.CategoryNotMatchException;
import mock.repository.FakeAboutRepository;

public class AboutCommandServiceTest {
	private AboutCommandService aboutCommandService;
	private FakeAboutRepository fakeAboutRepository;

	@BeforeEach
	public void init() {
		fakeAboutRepository = new FakeAboutRepository();
		aboutCommandService = new AboutCommandService(fakeAboutRepository);

		fakeAboutRepository.save(
			About.create(DEPT_INTRO, HISTORY, "detailCategory", "about content")
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
		Long result = aboutCommandService.createAbout(mainCategory, subCategory, detail, content);

		// then
		assertEquals(2L, result);
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
			.isInstanceOf(CategoryNotMatchException.class).hasMessage("메인 카테고리와 보조 카테고리가 일치하지 않습니다.");
	}

	@Test
	@DisplayName("updateAbout은 About을 수정할 수 있다")
	public void updateAbout_Success() {
		// given
		Long aboutId = 1L;
		String newContent = "newContent";

		// when
		aboutCommandService.updateAbout(aboutId, newContent);

		// then
		About about = fakeAboutRepository.findById(aboutId).orElse(null);

		assertNotNull(about);
		assertEquals(aboutId, about.getId());
		assertEquals(newContent, about.getContent());
	}

	@Test
	@DisplayName("updateAbout은 존재하지 않는 about 수정 요청 시 AboutNotFoundException을 발생시킨다")
	public void updateAbout_Throws_AboutNotFoundException() {
		// given
		Long aboutId = 10L;
		String newContent = "newContent";

		// when
		// then
		assertThatThrownBy(() -> aboutCommandService.updateAbout(aboutId, newContent))
			.isInstanceOf(AboutNotFoundException.class);
	}
}
