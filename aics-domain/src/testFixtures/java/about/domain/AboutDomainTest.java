package about.domain;

import static kgu.developers.domain.about.domain.MainCategory.DEPT_INTRO;
import static kgu.developers.domain.about.domain.SubCategory.HISTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;

public class AboutDomainTest {
	@Test
	@DisplayName("ABOUT 객체를 생성할 수 있다.")
	public void createAbout_Success() {
		// given
		MainCategory mainCategory = DEPT_INTRO;
		SubCategory subCategory = HISTORY;
		String detailCategory = "test";
		String content = "testContent";

		// when
		About about = About.create(mainCategory, subCategory, detailCategory, content);

		// then
		assertEquals(about.getMainCategory(), mainCategory);
		assertEquals(about.getSubCategory(), subCategory);
		assertEquals(about.getDetailCategory(), detailCategory);
		assertEquals(about.getContent(), content);
	}

	@Test
	@DisplayName("UpdateContent는 About 객체의 content를 수정할 수 있다.")
	public void updateContent_Success() {
		// given
		String detailCategory = "test";
		String content = "testContent";
		About about = About.create(DEPT_INTRO, HISTORY, detailCategory, content);

		String updateContent = "updateContent";

		// when
		about.updateContent(updateContent);

		// then
		assertEquals(about.getContent(), updateContent);
	}
}
