package about.domain;

import static kgu.developers.domain.about.domain.Category.DEPT_INTRO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;

public class AboutDomainTest {
	@Test
	@DisplayName("ABOUT 객체를 생성할 수 있다.")
	public void createAbout_Success() {
		// given
		Category category = DEPT_INTRO;
		String description = "test description";
		String content = "test content";

		// when
		About about = About.create(category, description, content);

		// then
		assertEquals(category, about.getCategory());
		assertEquals(description, about.getDescription());
		assertEquals(content, about.getContent());
	}

	@Test
	@DisplayName("UpdateDescription은 About 객체의 description을 수정할 수 있다.")
	public void updateDescription_Success() {
		// given
		About about = About.create(DEPT_INTRO, "test description", "test content");
		String updateDescription = "update description";

		// when
		about.updateDescription(updateDescription);

		// then
		assertEquals(updateDescription, about.getDescription());
	}

	@Test
	@DisplayName("UpdateContent는 About 객체의 content를 수정할 수 있다.")
	public void updateContent_Success() {
		// given
		About about = About.create(DEPT_INTRO, "test description", "test content");
		String updateContent = "update content";

		// when
		about.updateContent(updateContent);

		// then
		assertEquals(updateContent, about.getContent());
	}
}
